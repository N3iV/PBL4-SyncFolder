class Path {
  constructor() {
    this.home = "/";
    this.login = "/login";
    this.register = "/register";
    this.folders = "/folders";
    this.folderDetail = "folders/:idFolder";
    this.notFound = "*";
  }
}
export const path = new Path();
