import { unwrapResult } from "@reduxjs/toolkit";
import { Button, List, Pagination } from "antd";
import axios from "axios";
import React, { useEffect, useRef, useState } from "react";
import { FaFileAlt, FaFolder, FaInfo, FaShare, FaTrash } from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import useWebSocket from "react-use-websocket";
import ModalInfo from "../components/ModalInfo";
import { path } from "../constant/path";
import useQuery from "../hooks/useQuery";
import Default from "../layout/Default";
import { foldersActions, searchFolders } from "../slices/folders.slice";
import { isDeleteSocket, isSharingSocket } from "../utils/helper";
const Home = () => {
  const ref = useRef();
  const { profile } = useSelector((state) => state.auth);
  const { search } = useQuery();
  const [searchRes, setSearchRes] = useState([]);
  const { sharedFolders, personalFolder } = useSelector(
    (state) => state.folders
  );
  const dispatch = useDispatch();
  const [currPage, setCurrPage] = useState(1);
  const [currFolder, setCurrFolder] = useState({});
  const navigate = useNavigate();

  const showModalInfo = (item) => {
    setCurrFolder(item);
  };

  const [socketUrl, setSocketUrl] = useState(
    `ws://localhost:8080/SyncFolderPBL4/sync/${profile?.user?.id}`
  );
  const { sendMessage, lastMessage } = useWebSocket(socketUrl);

  useEffect(() => {
    const _searchFolders = async () => {
      const data = {
        id: profile?.user?.id,
        name: search,
      };
      const res = await dispatch(searchFolders(data));
      unwrapResult(res);
      setSearchRes(res.payload);
    };
    if (search) _searchFolders();
  }, [dispatch, profile?.user?.id, search]);
  useEffect(() => {
    if (lastMessage !== null) {
      const { data, message } = JSON.parse(lastMessage?.data);
      toast.success(message, {
        position: "top-right",
        autoClose: 2000,
      });
      dispatch(foldersActions.updatePersonalFolder(data));
      if (isSharingSocket(message)) {
        dispatch(foldersActions.updateSharedBy(data));
      }
      if (isDeleteSocket(message)) {
        const folderName = message
          .split("đã xóa thành công Directory")?.[1]
          ?.trim();
        dispatch(foldersActions.deleteShareFolders(folderName));
      }
    }
  }, [dispatch, lastMessage]);

  const handleSelectMenu = async (value) => {
    navigate(path.folders + `/${value.key}`);
  };

  const typeOfFile = Object.freeze({
    Directory: <FaFolder />,
    File: <FaFileAlt />,
  });

  const [url, setUrl] = useState("");
  const [name, setName] = useState("");
  const getFile = () => {
    return axios.get(
      "http://localhost:8080/SyncFolderPBL4/api/folders/1/download",
      {
        responseType: "blob",
        headers: {
          "Content-Type": "application/plain",
        },
      }
    );
  };
  const handleDownloadFile = async (id) => {
    try {
      const res = getFile();
      unwrapResult(res);
      const url = URL.createObjectURL(new Blob([res.payload]));
      setUrl(url);
      setName("test");
      ref.current?.click();
      URL.revokeObjectURL(url);
    } catch (error) {}
  };
  const handleDelete = async (item) => {
    setSocketUrl(
      `ws://localhost:8080/SyncFolderPBL4/sync/${profile?.user?.id}`
    );
    sendMessage(`{
        "func": "delete",
        "contentMsg": "{fileId: ${item.id}}"
    }`);
  };

  const onShowSizeChange = (curr) => {
    setCurrPage(curr);
  };

  const [isModalOpen, setIsModalOpen] = useState(false);
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
  return (
    <Default
      onMenuSelect={handleSelectMenu}
      setIsModalShareOpen={setIsModalOpen}
      showModalShare={isModalOpen}
    >
      <ModalInfo item={currFolder} />
      <List
        className="h-3/4 mt-4"
        itemLayout="horizontal"
        dataSource={search ? searchRes : personalFolder?.files}
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
            <a href={url} className="hidden" download={name} ref={ref}></a>

            <Button
              shape="round"
              className="ml-12"
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
      {personalFolder?.files?.numberOfPage && (
        <div className="flex items-center justify-center mt-4">
          <Pagination
            defaultCurrent={currPage}
            current={currPage}
            total={personalFolder?.files?.numberOfPage * 10}
            onChange={onShowSizeChange}
          />
        </div>
      )}
    </Default>
  );
};

export default Home;
