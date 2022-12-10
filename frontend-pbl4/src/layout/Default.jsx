import { UserOutlined } from "@ant-design/icons";
import { unwrapResult } from "@reduxjs/toolkit";
import {
  Avatar,
  Button,
  Checkbox,
  Input,
  Layout,
  Menu,
  Modal,
  Select,
} from "antd";
import React, { useEffect, useState } from "react";
import {
  FaFolder,
  FaGripHorizontal,
  FaPlus,
  FaSearch,
  FaShare,
  FaUser,
} from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getUsers } from "../slices/auth.slice";
import {
  createFolder,
  folderShareWithMe,
  getFileById,
  getFolder,
  setPermission,
} from "../slices/folders.slice";
import { convertDataPersonToSelectOptions } from "../utils/helper";

const { Header, Content, Sider } = Layout;

const Default = ({
  onMenuSelect,
  children,
  showModalShare,
  setIsModalShareOpen,
}) => {
  const [personalFolder, setPersonalFolder] = useState([]);
  const [sharedFolders, setSharedFolders] = useState([]);
  const [users, setUsers] = useState([]);
  const { profile } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(() => {
    const _getFolder = async () => {
      try {
        const res = await dispatch(getFolder(profile.id));
        const sharedFoldersRes = await dispatch(folderShareWithMe(profile.id));
        //no clean code, bcz I lười :D
        unwrapResult(res);
        setSharedFolders(sharedFoldersRes.payload.files);

        setPersonalFolder(res.payload);
      } catch (error) {}
    };
    const _getUsers = async () => {
      try {
        const res = await dispatch(getUsers(profile.id));
        setUsers(res.payload.users);
      } catch (error) {}
    };
    _getFolder();
    _getUsers();
  }, [dispatch, profile.id]);
  function getItem(label, key, icon, children) {
    return {
      key,
      icon,
      children,
      label,
    };
  }
  const items = [
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
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [folderName, setFolderName] = useState("Untitled folder");
  const [userShare, setUserShare] = useState([]);
  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    const _createFolder = async () => {
      await dispatch(
        createFolder({
          name: folderName,
        })
      );
    };
    navigate(0);
    _createFolder();
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
    setUserShare([]);
    setShareOptions([]);
    try {
      const data = {
        userIds: [...userShare],
        fileId: Number(localStorage.getItem("IdFileShare")),
        readPermission: shareOptions.includes("read"),
        updatePermission: shareOptions.includes("update"),
      };
      const res = await dispatch(setPermission(data));
      unwrapResult(res);
      console.log(res);
      console.log(data, "data");
    } catch (error) {}
  };

  const handleChange = (value) => {
    setUserShare([...value]);
  };
  const options = [
    { label: "Read", value: "read" },
    { label: "Update", value: "update" },
  ];

  const [shareOptions, setShareOptions] = useState([]);
  const onCheckBoxChange = (values) => {
    setShareOptions([...values]);
  };
  return (
    <Layout className="min-h-screen">
      <Header>
        <div className="container mx-auto flex items-center justify-between">
          <div className="logo">
            <img
              src="https://ssl.gstatic.com/images/branding/product/1x/drive_2020q4_48dp.png"
              alt=""
            />
          </div>
          <div>
            <Input
              size="large"
              placeholder="Search ..."
              prefix={<FaSearch />}
              className="rounded-xl text-xl"
            />
          </div>
          <div className="flex items-center">
            <FaGripHorizontal className="text-xl" />
            <Avatar
              className="ml-4 flex justify-center items-center"
              icon={<UserOutlined />}
            />
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
          // defaultValue={["a10", "c12"]}
          onChange={handleChange}
          options={convertDataPersonToSelectOptions(users)}
        />
        <br />
        <Checkbox.Group
          options={options}
          defaultValue={["Read"]}
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
