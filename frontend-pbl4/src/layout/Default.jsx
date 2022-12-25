import { UserOutlined } from "@ant-design/icons";
import { unwrapResult } from "@reduxjs/toolkit";
import {
  Avatar,
  Button,
  Checkbox,
  Dropdown,
  Input,
  Layout,
  Menu,
  Modal,
  Select,
  Typography,
} from "antd";
import React, { useEffect, useMemo, useState } from "react";
import { FaFolder, FaPlus, FaSearch, FaShare, FaUser } from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { useWebSocket } from "react-use-websocket/dist/lib/use-websocket";
import { getUsers, logout } from "../slices/auth.slice";
import {
  foldersActions,
  folderShareWithMe,
  getFileById,
  getFolder,
} from "../slices/folders.slice";
import {
  convertDataPersonToSelectOptions,
  isSharingSocket,
} from "../utils/helper";

const { Header, Content, Sider } = Layout;

const Default = ({
  onMenuSelect,
  children,
  showModalShare,
  setIsModalShareOpen,
  setIsSetPermission,
}) => {
  const [personalFolder, setPersonalFolder] = useState([]);
  const { sharedFolders: data } = useSelector((state) => state.folders);
  const [sharedFolders, setSharedFolders] = useState(data?.files);
  const [searchVal, setSearchVal] = useState("");
  const [users, setUsers] = useState([]);
  const { profile } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const { idFolder } = useParams();

  const [socketUrl, setSocketUrl] = useState(
    `ws://localhost:8080/SyncFolderPBL4/sync/${profile?.user?.id}}`
  );

  const { sendMessage, lastMessage } = useWebSocket(socketUrl);
  useEffect(() => {
    if (data) setSharedFolders(data.files);
  }, [data]);
  useEffect(() => {
    if (lastMessage !== null) {
      const { data, message } = JSON.parse(lastMessage?.data);
      console.log(message, "message from defaut");
      toast.success(message, {
        position: "top-right",
        autoClose: 2000,
      });
      if (isSharingSocket(message)) {
        setIsSetPermission((prev) => !prev);
        dispatch(foldersActions.updateSharedBy(data));
      }
    }
  }, [lastMessage]);
  useEffect(() => {
    const _getFolder = async () => {
      try {
        console.log("hehehehe");
        const res = await dispatch(getFolder(profile?.user?.id));
        const sharedFoldersRes = await dispatch(
          folderShareWithMe(profile?.user?.id)
        );

        //no clean code, bcz I lười :D
        unwrapResult(res);
        setSharedFolders(sharedFoldersRes.payload.files);
        setPersonalFolder(res.payload);
        const data = { id: profile?.user?.id, folderID: res.payload.id };
        const folderByID = await dispatch(getFileById(data));
        unwrapResult(folderByID);
        await dispatch(foldersActions.getPersonalFolder(folderByID.payload));
      } catch (error) {}
    };
    const _getUsers = async () => {
      try {
        const res = await dispatch(getUsers(profile?.user?.id));
        setUsers(res.payload.users);
      } catch (error) {}
    };
    _getFolder();
    _getUsers();
  }, [dispatch, profile?.user?.id]);
  function getItem(label, key, icon, children) {
    return {
      key,
      icon,
      children,
      label,
    };
  }
  const items = useMemo(() => {
    const result = [
      getItem("Drive của tôi", "1", <FaUser />, [
        getItem(personalFolder.name, personalFolder.id, <FaFolder />),
      ]),
      getItem(
        "Chia sẻ với tôi",
        "sub1",
        <FaShare />,
        sharedFolders?.map((item) => getItem(item.name, item.id))
      ),
    ];
    return result;
  }, [sharedFolders]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [folderName, setFolderName] = useState("Untitled folder");
  const [shareOptions, setShareOptions] = useState([]);

  const [userShare, setUserShare] = useState([]);
  const navigate = useNavigate();
  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    setSocketUrl(
      `ws://localhost:8080/SyncFolderPBL4/sync/${profile?.user?.id}`
    );
    const msg = `{
      "func": "create",
      "contentMsg": "{'parentFolderId' : ${
        idFolder || profile?.user?.id
      }, 'folderName':'${folderName}'}"
  }`;
    sendMessage(msg);
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };
  const handleChangeFolderName = (e) => {
    setFolderName(e.target.value);
  };
  const handleModalShareCancel = () => {
    setIsModalShareOpen(false);
  };
  const handleModalShareOk = async () => {
    setIsModalShareOpen(false);

    setSocketUrl(
      `ws://localhost:8080/SyncFolderPBL4/sync/${profile?.user?.id}`
    );
    const msg = `{
      "func": "permission",
      "contentMsg": "{ 'userIds' : [${[...userShare]}],  'fileId' : ${Number(
      localStorage.getItem("IdFileShare")
    )}, 'readPermission': ${shareOptions.includes(
      "read"
    )}, 'updatePermission': ${shareOptions.includes("update")} }"
  }`;

    sendMessage(msg);
    setUserShare([]);
    setShareOptions([]);
  };

  const handleChange = (value) => {
    setUserShare([...value]);
  };
  const options = [
    { label: "Read", value: "read" },
    { label: "Update", value: "update" },
  ];

  const onCheckBoxChange = (values) => {
    setShareOptions([...values]);
  };

  const DropDown = (onClick) => {
    return (
      <Menu>
        <Menu.Item onClick={onClick}>
          <span>Đăng xuất</span>
        </Menu.Item>
      </Menu>
    );
  };

  const onMenuDropDownClick = (e) => {
    const res = dispatch(logout());
    unwrapResult(res);
  };
  const handleSearch = () => {
    navigate(`/?search=${searchVal}`);
    setSearchVal("");
  };
  return (
    <Layout className="min-h-screen">
      <Header>
        <div className=" px-6 flex items-center justify-between">
          <Link to="/" className="logo font-bold text-2xl">
            Sync Folder
          </Link>
          <div className="flex h-12 items-center">
            <Input
              value={searchVal}
              onChange={(e) => setSearchVal(e.target.value)}
              size="large"
              placeholder="Search ..."
              prefix={<FaSearch />}
              className="rounded-xl text-xl"
            />
            <Button onClick={handleSearch} className="h-full rounded-lg ml-2">
              Search
            </Button>
          </div>
          <div className="flex items-center">
            <Dropdown
              overlay={DropDown(onMenuDropDownClick)}
              trigger={["click"]}
            >
              <span className="flex items-center ml-4">
                <Avatar src="" icon={<UserOutlined />} />
                <Typography.Text className=" !text-white px-2 m-0">
                  {profile?.user?.email}
                </Typography.Text>
              </span>
            </Dropdown>
          </div>
        </div>
      </Header>

      <Modal
        title="Share for"
        open={showModalShare}
        onOk={handleModalShareOk}
        onCancel={handleModalShareCancel}
      >
        <Select
          mode="multiple"
          allowClear
          style={{ width: "100%" }}
          placeholder="Please select"
          defaultValue={userShare}
          value={userShare}
          // defaultValue={["a10", "c12"]}
          onChange={handleChange}
          options={convertDataPersonToSelectOptions(users)}
        />
        <br />
        <Checkbox.Group
          value={shareOptions}
          options={options}
          defaultValue={"read"}
          onChange={onCheckBoxChange}
        />
      </Modal>

      <Modal
        title="New folder"
        open={isModalOpen}
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Input
          onChange={handleChangeFolderName}
          placeholder="Untitled folder"
          defaultValue={folderName}
        />
      </Modal>
      <Layout>
        <Sider
          style={{ width: "400px", minWidth: "400px" }}
          className=" min-h-screen site-layout-background"
        >
          <Button
            className="my-4 flex items-center justify-center w-3/4 rounded-3xl font-bold text-xl py-5"
            icon={<FaPlus className="mr-3" />}
            onClick={showModal}
          >
            Mới
          </Button>

          <Menu
            mode="inline"
            defaultSelectedKeys={["1"]}
            defaultOpenKeys={["sub1"]}
            style={{
              height: "100%",
              borderRight: 0,
            }}
            items={items}
            onClick={onMenuSelect}
          ></Menu>
        </Sider>
        <Layout
          style={{
            padding: "0 24px 24px",
          }}
        >
          <Content className="site-layout-background min-h-screen w-full px-12 py-4">
            {children}
          </Content>
        </Layout>
      </Layout>
    </Layout>
  );
};

export default Default;
