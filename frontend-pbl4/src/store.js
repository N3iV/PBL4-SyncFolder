import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./slices/auth.slice";
import foldersReducer from "./slices/folders.slice";

const rootReducer = {
  auth: authReducer,
  folders: foldersReducer,
};
const store = configureStore({
  reducer: rootReducer,
  devTools: process.env.NODE_ENV === "development",
  middleware: (getDefaultMiddleware) => [
    ...getDefaultMiddleware({ serializableCheck: false }),
  ],
});
export default store;
