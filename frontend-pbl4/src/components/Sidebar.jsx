import { Button, Input, Menu, Modal } from "antd";
import Sider from "antd/lib/layout/Sider";
import React, { useState } from "react";
import { FaPlus, FaShare, FaUser } from "react-icons/fa";

const Sidebar = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [folderName, setFolderName] = useState("Untitled folder");
  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };
  const handleChangeFolderName = (e) => {
    console.log(e.target.value);
  };
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
  return (
    <>
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
    </>
  );
};

export default Sidebar;
