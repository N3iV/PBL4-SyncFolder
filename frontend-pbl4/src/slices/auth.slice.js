import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import authApi from "../api/auth";
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
//   export const registerMember = createAsyncThunk(
//     "auth/registerMember",
//     payloadCreator(authApi.registerMember)
//   );
//   export const updateMe = createAsyncThunk(
//     "auth/updateProfile",
//     payloadCreator(authApi.updateProfile)
//   );
//   export const changePassword = createAsyncThunk(
//     "auth/changePass",
//     payloadCreator(authApi.changePass)
//   );
const handleAuthFulfilled = (state, action) => {
  const _data = action.payload.data;
  state.profile = _data;
  // localStorage.setItem(LocalStorage.user, JSON.stringify(_data.user));
  // localStorage.setItem(LocalStorage.hotel, JSON.stringify(_data.hotel));
};
const handleRegisterFulfilled = (state, action) => {
  state.profile.user = action.payload.data;
  // localStorage.setItem(LocalStorage.user, JSON.stringify(action.payload.data));
};
const handleUnauth = (state) => {
  state.profile = {};
  // localStorage.removeItem(LocalStorage.user);
  // localStorage.removeItem(LocalStorage.hotel);
  // localStorage.removeItem(LocalStorage.filters);
};

const auth = createSlice({
  name: "auth",
  initialState: {
    profile: {},
  },
  reducers: {
    unauthorize: handleUnauth,
  },
  extraReducers: {
    [register.fulfilled]: handleRegisterFulfilled,
    [login.fulfilled]: handleAuthFulfilled,
    [logout.fulfilled]: handleUnauth,
  },
});

const authReducer = auth.reducer;
export const unauthorize = auth.actions.unauthorize;
export default authReducer;
