import LocalStorage from "../constant/localStorage";
import http from "../utils/http";
const user = JSON.parse(localStorage.getItem(LocalStorage.user));
const foldersApi = {
  getFolders(config) {
    console.log(user.id);
    return http.get(`/users/${user.id}/folders`, config);
  },
  getFileById(id, config) {
    return http.get(`users/${user.id}/folders/file?fileId=${id}`, config);
  },
  downloadFile(id, config) {
    return http.get(`folders/file/${id}/download`, config);
  },
  createFolder(data, config) {
    return http.post(`folders/1?name=Foldertest`, data, config);
  },
};

export default foldersApi;
