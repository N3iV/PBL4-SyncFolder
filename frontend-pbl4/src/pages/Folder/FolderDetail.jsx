import axios from "axios";
import React, { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useParams } from "react-router-dom";
const FolderDetail = () => {
  const dispatch = useDispatch();
  const { idFolder } = useParams();
  console.log(idFolder);
  const [file, setFile] = useState({});
  useEffect(() => {
    const getFile = async () => {
      try {
        var config = {
          method: "get",
          url: "http://localhost:8080/SyncFolderPBL4/api/users/1/folders/file?fileId=5",
          headers: {
            "Content-Type": "application/pdf",
          },
        };

        axios(config)
          .then(function (response) {
            console.log(JSON.stringify(response.data));
          })
          .catch(function (error) {
            console.log(error);
          });
      } catch (error) {}
    };
    getFile();
  }, []);
  console.log(file);
  return <div></div>;
};

export default FolderDetail;
