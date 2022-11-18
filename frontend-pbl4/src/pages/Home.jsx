import { unwrapResult } from "@reduxjs/toolkit";
import { Button, List, Modal, Pagination } from "antd";
import axios from "axios";
import React, { useEffect, useRef, useState } from "react";
import {
  FaCloudDownloadAlt,
  FaFileAlt,
  FaFolder,
  FaShare,
  FaTrash,
} from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { path } from "../constant/path";
import Default from "../layout/Default";
import { getFileById } from "../slices/folders.slice";
const Home = () => {
  const ref = useRef();
  const { folders } = useSelector((state) => state.folders);
  const { profile } = useSelector((state) => state.auth);
  const [files, setFiles] = useState([]);
  const dispatch = useDispatch();
  const [currPage, setCurrPage] = useState(1);
  const [currFolder, setCurrFolder] = useState(1);
  const [personalFolder, setPersonalFolder] = useState([]);
  const getFolder = async () => {
    const data = { id: profile.id, folderID: currFolder, page: currPage };
    const res = await dispatch(getFileById(data));
    console.log(res);
    unwrapResult(res);
    setFiles(res.payload);
  };
  const handleSelectMenu = async (value) => {
    setCurrFolder(value.key);
  };
  useEffect(() => {
    const getFolder = async () => {
      const data = { id: profile.id, folderID: currFolder, page: currPage };
      const res = await dispatch(getFileById(data));
      console.log(res);
      unwrapResult(res);
      setFiles(res.payload);
    };
    getFolder();
  }, [currFolder, currPage, dispatch, profile.id]);
  const type = [
    {
      type: "Directory",
      icon: <FaFolder />,
    },
    {
      type: "File",
      icon: <FaFileAlt />,
    },
  ];
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
      console.log(res.payload);
      const url = URL.createObjectURL(new Blob([res.payload]));
      setUrl(url);
      setName("test");
      ref.current?.click();
      URL.revokeObjectURL(url);
    } catch (error) {}
  };
  const handleDelete = async (item) => {
    console.log(item);
    if (item?.type?.name === "File") {
      //delete file
    } else if (item?.type?.name === "Directory") {
      ///delete folder
    }
  };
  const handleShare = async (data) => {
    console.log(data);
  };
  const onShowSizeChange = (curr) => {
    setCurrPage(curr);
  };

  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleModalShareOk = () => {
    setIsModalOpen(false);
  };

  const handleModalShareCancel = () => {
    setIsModalOpen(false);
  };
  return (
    <Default
      onMenuSelect={handleSelectMenu}
      setIsModalShareOpen={setIsModalOpen}
      showModalShare={isModalOpen}
    >
      {/* <Breadcrumb className="p-4" separator=">">
        <Breadcrumb.Item className="text-xl">
          Được chia sẻ với tôi
        </Breadcrumb.Item>
        <Breadcrumb.Item className="text-xl font-semibold">
          Provo - Writing
        </Breadcrumb.Item>
      </Breadcrumb> */}

      <List
        itemLayout="horizontal"
        dataSource={folders.files || files.files}
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
              onClick={() => handleDownloadFile(item.id)}
            >
              <FaCloudDownloadAlt />
            </Button>

            <Button shape="round" className="ml-4" onClick={showModal}>
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

export default Home;
