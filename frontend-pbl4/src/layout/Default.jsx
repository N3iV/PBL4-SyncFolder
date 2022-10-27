import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Avatar, Button, Input, Layout, Menu } from "antd";
import React from "react";
import { FaGripHorizontal, FaPlus, FaSearch, FaUser } from "react-icons/fa";
import Sidebar from "../components/Sidebar";

const { Header, Content, Sider } = Layout;

const Default = ({ children }) => {
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
      <Layout>
        <Sidebar />
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
