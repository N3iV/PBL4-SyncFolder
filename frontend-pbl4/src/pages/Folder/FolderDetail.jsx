import { unwrapResult } from "@reduxjs/toolkit";
import { Button, List, Pagination } from "antd";
import React, { useEffect, useState } from "react";
import {
  FaCloudDownloadAlt,
  FaFileAlt,
  FaFolder,
  FaShare,
  FaTrash,
} from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { useWebSocket } from "react-use-websocket/dist/lib/use-websocket";
import { path } from "../../constant/path";
import Default from "../../layout/Default";
import { getFileById } from "../../slices/folders.slice";
const FolderDetail = () => {
  const dispatch = useDispatch();
  const { profile } = useSelector((state) => state.auth);
  const { idFolder } = useParams();
  const [files, setFiles] = useState({});
  const [currPage, setCurrPage] = useState(1);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const getFolder = async () => {
      const data = { id: profile.id, folderID: idFolder };
      console.log(data, "test detail");
      const res = await dispatch(getFileById(data));
      unwrapResult(res);
      setFiles(res.payload);
    };
    getFolder();
  }, [dispatch, idFolder, profile.id]);

  const [socketUrl, setSocketUrl] = useState(
    `ws://localhost:8080/SyncFolderPBL4/sync/1/${profile.id}`
  );

  const { sendMessage, lastMessage } = useWebSocket(socketUrl);
  useEffect(() => {
    if (lastMessage !== null) {
      const { data, message } = JSON.parse(lastMessage?.data);
      setFiles(data);
      console.log(message, "message");
      console.log(data, "socket data home ");
      toast.success(message, {
        position: "top-right",
        autoClose: 2000,
      });
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
    setSocketUrl(
      `ws://localhost:8080/SyncFolderPBL4/sync/${item.ownerId}/${profile.id}`
    );
    sendMessage(`{
        "func": "delete",
        "contentMsg": "{fileId: ${item.id}}"
    }`);
    setSocketUrl(`ws://localhost:8080/SyncFolderPBL4/sync/1/${profile.id}`);
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
    console.log("test");
    navigate(path.folders + `/${value.key}`);
  };
  return (
    <Default
      onMenuSelect={handleSelectMenu}
      setIsModalShareOpen={setIsModalOpen}
      showModalShare={isModalOpen}
    >
      <List
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
              className="ml-12"
              // onClick={() => handleDownloadFile(item.id)}
            >
              <FaCloudDownloadAlt />
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
      {files.numberOfPage && (
        <Pagination
          defaultCurrent={currPage}
          current={currPage}
          total={files.numberOfPage * 10}
          onChange={onShowSizeChange}
        />
      )}
    </Default>
  );
};

export default FolderDetail;
