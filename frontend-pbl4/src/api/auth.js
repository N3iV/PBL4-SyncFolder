import http from "../utils/http";

const authApi = {
  register(data) {
    return http.post("users/register", data);
  },
  login(data) {
    return http.post("users/login", data);
  },
  getUsers(id) {
    return http.get(`users/${id}?page=1`);
  },
  logout() {
    return;
  },
};

export default authApi;
