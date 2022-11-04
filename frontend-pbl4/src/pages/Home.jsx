import { Breadcrumb, List } from "antd";
import React, { useEffect, useState } from "react";
import { FaFileAlt, FaFileExcel, FaFolder } from "react-icons/fa";
import Default from "../layout/Default";
import { Link } from "react-router-dom";
import { path } from "../constant/path";
import { useDispatch } from "react-redux";
import { getFolder } from "../slices/file.slice";
import { unwrapResult } from "@reduxjs/toolkit";
const Home = () => {
  const type = [
    {
      type: "folder",
      icon: <FaFolder />,
    },
    {
      type: "File",
      icon: <FaFileAlt />,
    },
  ];
  const typeOfFile = Object.freeze({
    Folder: <FaFolder />,
    File: <FaFileAlt />,
  });
  const [files, setFiles] = useState([]);
  const dispatch = useDispatch();
  useEffect(() => {
    const _getFolder = async () => {
      try {
        const res = await dispatch(getFolder());
        unwrapResult(res);
        setFiles(res.payload.files);
      } catch (error) {}
    };
    _getFolder();
  }, []);
  return (
    <Default>
      <Breadcrumb className="p-4" separator=">">
        <Breadcrumb.Item className="text-xl">
          Được chia sẻ với tôi
        </Breadcrumb.Item>
        <Breadcrumb.Item className="text-xl font-semibold">
          Provo - Writing
        </Breadcrumb.Item>
      </Breadcrumb>
      <List
        itemLayout="horizontal"
        dataSource={files}
        renderItem={(item, idx) => (
          <List.Item className="hover:bg-slate-200 px-4">
            <Link
              to={`${path.folders}/${path}`}
              className="flex items-center  cursor-pointer justify-between w-full"
            >
              <div className="flex items-center">
                <span className="text-xl">{typeOfFile[item?.type?.name]}</span>
                <span className="ml-4 text-xl text-gray-500">{item.name}</span>
              </div>
              <div>
                <span>{item.createdDate}</span>
              </div>
              <div>
                <span>{item.modifiedDate}</span>
              </div>
            </Link>
          </List.Item>
        )}
      />
    </Default>
  );
};

export default Home;
