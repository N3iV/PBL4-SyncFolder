import { unwrapResult } from "@reduxjs/toolkit";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import Default from "../layout/Default";
import { getUsers } from "../slices/auth.slice";
import { folderShareWithMe } from "../slices/folders.slice";

const ShareForMe = () => {
  const [sharedFolders, setSharedFolders] = useState([]);
  const { profile } = useSelector((state) => state.auth);

  const dispatch = useDispatch();
  useEffect(() => {
    const _getFolder = async () => {
      try {
        const res = await dispatch(folderShareWithMe(profile.id));
        unwrapResult(res);
        setSharedFolders(res.payload.files);
      } catch (error) {}
    };
    _getFolder();
  }, [dispatch, profile.id]);
  return <Default>ShareForMe</Default>;
};

export default ShareForMe;
