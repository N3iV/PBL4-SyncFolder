import { unwrapResult } from "@reduxjs/toolkit";
import { Button, List, Pagination } from "antd";
import React, { useEffect, useState } from "react";
import { FaFileAlt, FaFolder, FaInfo, FaShare, FaTrash } from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { useWebSocket } from "react-use-websocket/dist/lib/use-websocket";
import ModalInfo from "../../components/ModalInfo";
import { path } from "../../constant/path";
import Default from "../../layout/Default";
import { foldersActions, getFileById } from "../../slices/folders.slice";
import { isDeleteSocket, isSharingSocket } from "../../utils/helper";
const FolderDetail = () => {
  const dispatch = useDispatch();
  const { profile } = useSelector((state) => state.auth);
  const { sharedFolders, folders } = useSelector((state) => state.folders);
  const [isSetPermission, setIsSetPermission] = useState(false);

  const { idFolder } = useParams();
  const [files, setFiles] = useState({});
  const [currPage, setCurrPage] = useState(1);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currFolder, setCurrFolder] = useState({});

  const showModalInfo = (item) => {
    setCurrFolder(item);
  };

  const navigate = useNavigate();

  const getFolder = async () => {
    const data = { id: profile?.user?.id, folderID: idFolder };
    const res = await dispatch(getFileById(data));
    unwrapResult(res);
    setFiles(res.payload);
  };
  useEffect(() => {
    getFolder();
  }, [isSetPermission, idFolder, profile?.user?.id]);

  const [socketUrl, setSocketUrl] = useState(
    `ws://localhost:8080/SyncFolderPBL4/sync/${profile?.user?.id}`
  );

  const { sendMessage, lastMessage } = useWebSocket(socketUrl);
  useEffect(() => {
    if (lastMessage !== null) {
      const { data, message } = JSON.parse(lastMessage?.data);
      setFiles(data);
      console.log(message, "msg");
      console.log(data, "socket data home ");
      toast.success(message, {
        position: "top-right",
        autoClose: 2000,
      });
      if (isSharingSocket(message)) {
        getFolder();
        dispatch(foldersActions.updateSharedBy(data));
      }
      if (isDeleteSocket(message)) {
        const folderName = message
          .split("đã xóa thành công Directory")?.[1]
          ?.trim();
        dispatch(foldersActions.deleteShareFolders(folderName));
      }
    }
  }, [lastMessage]);
  //   const getFile = async () => {
  //     try {
  //       var config = {
  //         method: "get",
  //         url: "http://localhost:8080/SyncFolderPBL4/api/users/1/folders/file?fileId=5",
  //         headers: {
  //           "Content-Type": "application/pdf",
  //         },
  //       };

  //       axios(config)
  //         .then(function (response) {
  //           console.log(JSON.stringify(response.data));
  //         })
  //         .catch(function (error) {
  //           console.log(error);
  //         });
  //     } catch (error) {}
  //   };
  //   getFile();
  // }, []);
  const typeOfFile = Object.freeze({
    Directory: <FaFolder />,
    File: <FaFileAlt />,
  });

  const handleDelete = async (item) => {
    sendMessage(`{
        "func": "delete",
        "contentMsg": "{fileId: ${item.id}}"
    }`);
  };

  const onShowSizeChange = (curr) => {
    setCurrPage(curr);
  };
  const handleShare = (id) => {
    const showModal = () => {
      setIsModalOpen(true);
    };
    const setFileId = (id) => {
      localStorage.setItem("IdFileShare", JSON.stringify(id));
    };
    showModal();
    setFileId(id);
  };
  const handleSelectMenu = async (value) => {
    navigate(path.folders + `/${value.key}`);
  };
  return (
    <Default
      onMenuSelect={handleSelectMenu}
      setIsModalShareOpen={setIsModalOpen}
      showModalShare={isModalOpen}
      setIsSetPermission={setIsSetPermission}
    >
      <ModalInfo item={currFolder} />
      <List
        className="h-3/4 mt-4"
        itemLayout="horizontal"
        dataSource={files.files}
        renderItem={(item, idx) => (
          <List.Item className="hover:bg-slate-200 px-4 flex justify-between">
            <Link
              to={`${path.folders}/${item.id}`}
              className="flex items-center  cursor-pointer justify-between w-full"
            >
              <div className="flex items-center">
                <span className="text-xl">{typeOfFile[item?.type?.name]}</span>
                <span className="ml-4 text-xl text-gray-500">{item.name}</span>
              </div>
              <div>
                <span className="inline-block ">{item.createdDate}</span>
                <span className="inline-block ml-8">{item.modifiedDate}</span>
              </div>
            </Link>
            {/* <a href={url} className="hidden" download={name} ref={ref}></a> */}

            <Button
              shape="round"
              className="ml-4"
              onClick={() => showModalInfo(item)}
            >
              <FaInfo />
            </Button>
            <Button
              shape="round"
              className="ml-4"
              onClick={() => handleShare(item.id)}
            >
              <FaShare />
            </Button>
            <Button
              shape="round"
              className="ml-4"
              onClick={() => handleDelete(item)}
            >
              <FaTrash />
            </Button>
          </List.Item>
        )}
      />
      {files.numberOfPage ? (
        <div className="flex items-center justify-center mt-4">
          <Pagination
            defaultCurrent={currPage}
            current={currPage}
            total={files.numberOfPage * 10}
            onChange={onShowSizeChange}
          />
        </div>
      ) : null}
    </Default>
  );
};

export default FolderDetail;
