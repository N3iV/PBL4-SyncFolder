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
export const setPermission = createAsyncThunk(
  "folder/setPermission",
  payloadCreator(foldersApi.setPermission)
);
export const deleteFile = createAsyncThunk(
  "folder/file/delete",
  payloadCreator(foldersApi.deleteFile)
);
export const deleteFolder = createAsyncThunk(
  "folder/delete",
  payloadCreator(foldersApi.deleteFolder)
);

const handleFulfilled = (state, action) => {
  localStorage.setItem(
    LocalStorage.folders,
    JSON.stringify(action.payload.files)
  );
};
const getShareFolders = (state, action) => {
  state.sharedFolders = action.payload;
};
const updateSharedBy = (state, action) => {
  state.sharedFolders = action.payload;
};
const getFolderFulfilled = (state, action) => {
  state.personalFolder = action.payload;
};
const folders = createSlice({
  name: "folders",
  initialState: {
    personalFolder: [],
    sharedFolders: [],
  },
  reducers: {
    updateSharedBy,
  },
  extraReducers: {
    [createFolder.fulfilled]: handleFulfilled,
    [folderShareWithMe.fulfilled]: getShareFolders,
    [getFileById.fulfilled]: getFolderFulfilled,
  },
});

const foldersReducer = folders.reducer;
export const foldersActions = folders.actions;
export default foldersReducer;
