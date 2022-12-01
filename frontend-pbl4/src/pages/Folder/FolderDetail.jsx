import { unwrapResult } from "@reduxjs/toolkit";
import { List } from "antd";
import axios from "axios";
import React, { useEffect, useState } from "react";
import { FaFileAlt, FaFolder } from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { Link, useParams } from "react-router-dom";
import { path } from "../../constant/path";
import { getFileById } from "../../slices/folders.slice";
const FolderDetail = () => {
  const dispatch = useDispatch();
  const { profile } = useSelector((state) => state.auth);
  const { idFolder } = useParams();
  const [files, setFiles] = useState({});
  useEffect(() => {
    const getFolder = async () => {
      const data = { id: profile.id, folderID: idFolder };
      const res = await dispatch(getFileById(data));
      unwrapResult(res);
      setFiles(res.payload);
    };
    getFolder();
  }, [dispatch, profile.id]);
  // useEffect(() => {
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

  return (
    <div>
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
          </List.Item>
        )}
      />
    </div>
  );
};

export default FolderDetail;
