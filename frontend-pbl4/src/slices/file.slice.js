import { createAsyncThunk } from "@reduxjs/toolkit";
import fileApi from "../api/user";
import { payloadCreator } from "../utils/helper";

export const getFolder = createAsyncThunk(
  "folder/getFile",
  payloadCreator(fileApi.getFolders)
);
