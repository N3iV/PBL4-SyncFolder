import http from "../utils/http";

const fileApi = {
  getFolders(data) {
    return http.get(`users/1/file?fileId=1&page=1`, data);
  },
};

export default fileApi;
