export const isEmail = () =>
  /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

export const payloadCreator = (asyncFunc) => async (arg, thunkAPI) => {
  try {
    const res = await asyncFunc(arg);
    return res;
  } catch (error) {
    return thunkAPI.rejectWithValue(error);
  }
};

export const formatMoney = (value, character = ".") =>
  String(value).replace(/\B(?=(\d{3})+(?!\d))/g, character);

// export const formatDate = (date) =>
//   moment(date).format("YYYY-MM-DD").toString();

// export const getDayOfBooking = (date) => moment(date).format("DD").toString();

export const convertDataPersonToSelectOptions = (data) =>
  data?.map((item) => ({
    label: item.email,
    value: item.id,
  }));

export const isSharingSocket = (msg) =>
  msg.includes(" đã cấp quyền") || msg.includes("Chia sẻ quyền");

export const isDeleteSocket = (msg) => msg.includes(" đã xóa");

export const isCreateSocket = (msg) => msg.includes(" đã tạo");

export const formatPath = (path) => {
  const pathArr = path.split("\\");
  const newPath = pathArr.slice(0, pathArr.length - 1).join("/");
  return newPath;
};
