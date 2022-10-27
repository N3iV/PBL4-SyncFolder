import { Breadcrumb, List } from "antd";
import React from "react";
import { FaFileExcel, FaFolder } from "react-icons/fa";
import Default from "../layout/Default";
import { Link } from "react-router-dom";
import { path } from "../constant/path";
const Home = () => {
  const data = [
    {
      type: "folder",
      icon: <FaFolder />,
      title: "Content",
    },
    {
      type: "folder",
      icon: <FaFolder />,
      title: "Design",
    },
    {
      type: "folder",
      icon: <FaFolder />,
      title: "Dev",
    },
    {
      type: "file-excel",
      icon: <FaFileExcel />,
      title: "Bonus",
    },
  ];
  return (
    <Default>
      <Breadcrumb className="p-4" separator=">">
        <Breadcrumb.Item className="text-xl">
          Được chia sẻ với tôi
        </Breadcrumb.Item>
        <Breadcrumb.Item className="text-xl font-semibold">
          Provo - Writing
        </Breadcrumb.Item>
      </Breadcrumb>
      <List
        itemLayout="horizontal"
        dataSource={data}
        renderItem={(item, idx) => (
          <List.Item className="hover:bg-slate-200 px-4">
            <Link
              to={`${path.folders}/${idx}`}
              className="flex items-center  cursor-pointer"
            >
              <span className="text-xl">{item.icon}</span>
              <span className="ml-4 text-xl text-gray-500">{item.title}</span>
            </Link>
          </List.Item>
        )}
      />
    </Default>
  );
};

export default Home;
