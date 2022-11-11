import { Breadcrumb, Button, List } from "antd";
import React, { useEffect, useRef, useState } from "react";
import {
  FaCloudDownloadAlt,
  FaFileAlt,
  FaFileExcel,
  FaFolder,
  FaTrash,
} from "react-icons/fa";
import Default from "../layout/Default";
import { Link } from "react-router-dom";
import { path } from "../constant/path";
import { useDispatch, useSelector } from "react-redux";
import { downloadFile, getFileById, getFolder } from "../slices/folders.slice";
import { unwrapResult } from "@reduxjs/toolkit";
import axios from "axios";
const Home = () => {
  const ref = useRef();
  const { folders } = useSelector((state) => state.folders);
  const { profile } = useSelector((state) => state.auth);
  const [files, setFiles] = useState([]);
  const dispatch = useDispatch();
  const [personalFolder, setPersonalFolder] = useState([]);
  const handleSelectMenu = async (value) => {
    console.log(value);
    const data = { id: profile.id, folderID: value.key };
    const res = await dispatch(getFileById(data));
    unwrapResult(res);
    setFiles(res.payload.files);
  };
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
  return (
    <Default onMenuSelect={handleSelectMenu}>
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
        dataSource={folders.files || files}
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
            <Button
              shape="round"
              className="ml-12"
              onClick={() => handleDelete(item)}
            >
              <FaTrash />
            </Button>
          </List.Item>
        )}
      />
    </Default>
  );
};

export default Home;
