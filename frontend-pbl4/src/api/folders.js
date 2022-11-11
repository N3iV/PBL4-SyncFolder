import LocalStorage from "../constant/localStorage";
import http from "../utils/http";
const foldersApi = {
  getFolders(id, config) {
    return http.get(`/users/${id}/folders`, config);
  },

  getFileById(data) {
    console.log(data);
    return http.get(`users/${data.id}/folders/${data.folderID}?page=1`);
  },
  downloadFile(id, config) {
    return http.get(`folders/file/${id}/download`, config);
  },
  createFolder(folderId) {
    return http.post(`folders/1`, folderId);
  },
  sharedFolders(id, config) {
    return http.get(`/users/${id}/folders/share?page=1`, config);
  },
};

export default foldersApi;
