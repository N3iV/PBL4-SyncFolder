import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import foldersApi from "../api/folders";
import LocalStorage from "../constant/localStorage";
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
export const folderShareWithMe = createAsyncThunk(
  "folder/shareWithMe",
  payloadCreator(foldersApi.sharedFolders)
);

const handleFulfilled = (state, action) => {
  const _data = action.payload;
  console.log(_data);
  state.folders = _data;
  localStorage.setItem(LocalStorage.folders, JSON.stringify(state.folders));
};
const folders = createSlice({
  name: "folders",
  initialState: {
    folders: [],
  },

  extraReducers: {
    [createFolder.fulfilled]: handleFulfilled,
  },
});

const foldersReducer = folders.reducer;
export default foldersReducer;
