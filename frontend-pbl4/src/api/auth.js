import http from "../utils/http";

const authApi = {
  register(data) {
    return http.post("users/register", data);
  },
  login(data) {
    return http.post("users/login", data);
  },
  logout() {
    return;
  },
};

export default authApi;
