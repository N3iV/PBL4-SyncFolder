import { Modal, Tag } from "antd";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";

const ModalInfo = ({ item }) => {
  const [currentInfo, setCurrentInfo] = useState([]);
  const [isModalInfoOpen, setIsModalInfoOpen] = useState(false);

  const { users } = useSelector((state) => state.auth);

  const handleInfoModalOk = () => {
    setIsModalInfoOpen(false);
  };

  const mapperDataInfo = (users, item) => {
    const result = users?.users.map((user) => {
      const role = item?.roles?.find((role) => {
        if (role?.roleIds?.userId === user?.id) {
          return role;
        }
      });
      return {
        email: user.email,
        role: role,
      };
    });
    setCurrentInfo(result);
  };

  useEffect(() => {
    item?.id && setIsModalInfoOpen(true);
    mapperDataInfo(users, item);
  }, [item]);
  return (
    <Modal
      title="Quyền truy cập"
      open={isModalInfoOpen}
      onCancel={handleInfoModalOk}
      onOk={handleInfoModalOk}
    >
      {currentInfo?.map((item) => (
        <>
          {item.role && (
            <div key={item?.email} className="flex justify-between p-3">
              <h1>{item?.email}</h1>
              <Tag color="green">
                {item?.role?.updatePermission
                  ? "Chỉnh sửa"
                  : item?.role?.readPermission
                  ? "Chỉ đọc"
                  : ""}
              </Tag>
            </div>
          )}
        </>
      ))}
    </Modal>
  );
};

export default ModalInfo;
