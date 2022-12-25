import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import authApi from "../api/auth";
import LocalStorage from "../constant/localStorage";
import { payloadCreator } from "../utils/helper";

export const register = createAsyncThunk(
  "auth/register",
  payloadCreator(authApi.register)
);
export const login = createAsyncThunk(
  "auth/login",
  payloadCreator(authApi.login)
);
export const logout = createAsyncThunk(
  "auth/logout",
  payloadCreator(authApi.logout)
);
export const getUsers = createAsyncThunk(
  "auth/getUsers",
  payloadCreator(authApi.getUsers)
);

const handleGetUsersFullfilled = (state, action) => {
  state.users = action.payload;
};

const handleAuthFulfilled = (state, action) => {
  const _data = action.payload;
  state.profile = _data;
  localStorage.setItem(LocalStorage.user, JSON.stringify(state.profile));
};
const handleRegisterFulfilled = (state, action) => {
  state.profile.user = action.payload.data;
};
const handleUnauth = (state) => {
  state.profile = {};
  localStorage.removeItem(LocalStorage.user);
  localStorage.removeItem("IdFileShare");
  localStorage.removeItem("folders");
};

const auth = createSlice({
  name: "auth",
  initialState: {
    profile: JSON.parse(localStorage.getItem(LocalStorage.user)) || {},
  },
  reducers: {
    unauthorize: handleUnauth,
  },
  extraReducers: {
    [register.fulfilled]: handleRegisterFulfilled,
    [login.fulfilled]: handleAuthFulfilled,
    [logout.fulfilled]: handleUnauth,
    [getUsers.fulfilled]: handleGetUsersFullfilled,
  },
});

const authReducer = auth.reducer;
export const unauthorize = auth.actions.unauthorize;
export default authReducer;
