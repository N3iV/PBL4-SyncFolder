class Path {
  constructor() {
    this.home = "/";
    this.login = "/login";
    this.register = "/register";
    this.folders = "/folders";
    this.shareForMe = this.folders + "/share";
    this.folderDetail = "folders/:idFolder";
    this.notFound = "*";
  }
}
export const path = new Path();
