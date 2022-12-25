import http from "../utils/http";
const foldersApi = {
  getFolders(id, config) {
    return http.get(`/users/${id}/folders`, config);
  },

  getFileById(data) {
    console.log(data, "get data");
    console.log(`api: users/${data.id}/folders/${data.folderID}?page=1`);
    return http.get(`users/${data.id}/folders/${data.folderID}?page=1`);
  },
  downloadFile(id, config) {
    return http.get(`folders/file/${id}/download`, config);
  },
  createFolder(data) {
    const { id, name } = data;
    return http.post(`folders/${id}`, { name });
  },
  sharedFolders(id, config) {
    return http.get(`/users/${id}/folders/share?page=1`, config);
  },
  deleteFile(data) {
    return http.delete("folders/file", data);
  },
  deleteFolder(data) {
    return http.delete("folders", data);
  },
  setPermission(data) {
    return http.post("/users/folders/permission", data);
  },
};

export default foldersApi;
