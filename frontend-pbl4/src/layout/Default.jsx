import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Avatar, Button, Input, Layout, Menu, Modal } from "antd";
import React, { useState } from "react";
import {
  FaGripHorizontal,
  FaPlus,
  FaSearch,
  FaShare,
  FaUser,
} from "react-icons/fa";
import { useDispatch } from "react-redux";
import Sidebar from "../components/Sidebar";
import { createFolder } from "../slices/folders.slice";

const { Header, Content, Sider } = Layout;

const Default = ({ children }) => {
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
      getItem("Option 3", "2"),
      getItem("Option 4", "3"),
    ]),
    getItem("Chia sẻ với tôi", "sub1", <FaShare />, [
      getItem("Option 3", "4"),
      getItem("Option 4", "5"),
    ]),
  ];
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [folderName, setFolderName] = useState("Untitled folder");
  const dispatch = useDispatch();
  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    const _createFolder = async () => {
      const res = await dispatch(createFolder());
      console.log(res);
    };
    _createFolder();
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };
  const handleChangeFolderName = (e) => {
    console.log(e.target.value);
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
        <Sider className=" w-full min-h-screen site-layout-background">
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
