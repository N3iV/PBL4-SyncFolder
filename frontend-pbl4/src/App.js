import { Route, Routes } from "react-router-dom";
import { path } from "./constant/path";
import Folders from "./pages/Folder";
import FolderDetail from "./pages/Folder/FolderDetail";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";

function App() {
  return (
    <Routes>
      <Route path={path.home} element={<Home />} />
      <Route path={path.login} element={<Login />} />
      <Route path={path.register} element={<Register />} />
      <Route path={path.folders} element={<Folders />} />
      <Route path={path.folderDetail} element={<FolderDetail />} />
    </Routes>
  );
}

export default App;
