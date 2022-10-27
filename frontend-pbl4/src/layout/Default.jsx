import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { Button, Input, Layout, Menu } from "antd";
import React from "react";
import { FaPlus, FaSearch } from "react-icons/fa";

const { Header, Content, Sider } = Layout;

const Default = ({ children }) => {
  return (
    <Layout className="min-h-screen">
      <Header className="flex items-center justify-between">
        <div className="logo">My Dryve</div>
        <div>
          <Input
            size="large"
            placeholder="Search ..."
            prefix={<FaSearch />}
            className="rounded-xl text-xl"
          />
        </div>
        <div>Hello world</div>
      </Header>
      <Layout>
        <Sider className=" w-full min-h-screen site-layout-background">
          <Button
            className="my-4 flex items-center justify-center w-3/4 rounded-3xl font-bold text-xl py-5"
            icon={<FaPlus className="mr-3" />}
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
          >
            <Menu.Item>Drive của tôi</Menu.Item>
            <Menu.Item>Drive của tôi</Menu.Item>
            <Menu.Item>Drive của tôi</Menu.Item>
            <Menu.Item>Drive của tôi</Menu.Item>
            <Menu.Item>Drive của tôi</Menu.Item>
            <Menu.Item>Drive của tôi</Menu.Item>
          </Menu>
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
