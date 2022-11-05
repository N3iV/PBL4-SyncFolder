import { createAsyncThunk } from "@reduxjs/toolkit";
import foldersApi from "../api/folders";
import { payloadCreator } from "../utils/helper";

export const getFolder = createAsyncThunk(
  "folder/getFolder",
  payloadCreator(foldersApi.getFolders)
);
export const getFileById = createAsyncThunk(
  "file/getById",
  payloadCreator(foldersApi.getFileById)
);
export const downloadFile = createAsyncThunk(
  "file/download",
  payloadCreator(foldersApi.downloadFile)
);
export const createFolder = createAsyncThunk(
  "folder/create",
  payloadCreator(foldersApi.createFolder)
);
