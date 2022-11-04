import { unwrapResult } from "@reduxjs/toolkit";
import { Button, Col, Form, Input, Row } from "antd";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { register } from "../slices/auth.slice";
import styles from "../styles/pages/login.module.scss";

const Register = () => {
  const [error, setError] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const onFinish = async (values) => {
    const { firstname, lastname, email, password } = values;
    const data = { firstname, lastname, email, password };
    console.log(data);
    try {
      const res = await dispatch(register(data));
      unwrapResult(res);
      navigate("/");
    } catch (error) {
      console.log(error);
      if (error.status === 405) {
        setError(error.data.message);
      }
    }
  };

  const onFinishFailed = (errorInfo) => {
    console.log("Failed:", errorInfo);
  };
  return (
    <Row>
      <Col xl={8}>
        <div className={styles.registerLeft}></div>
      </Col>
      <Col xl={16}>
        <div className={styles.formContainer}>
          <Form
            className={styles.form}
            name="basic"
            initialValues={{
              remember: true,
            }}
            onFinish={onFinish}
            onFinishFailed={onFinishFailed}
            autoComplete="off"
          >
            <Form.Item>
              <div className="text-center flex items-center flex-col justify-center">
                <h1 className={styles.formHeading}>Tạo tài khoản </h1>
              </div>
            </Form.Item>

            <Form.Item label="Họ" name="firstname">
              <Input />
            </Form.Item>
            <Form.Item label="Tên" name="lastname">
              <Input />
            </Form.Item>

            <Form.Item
              label="Email"
              name="email"
              // validateStatus="error"
              // help={error || null}
            >
              <Input />
            </Form.Item>
            <Form.Item label="Mật khẩu" name="password">
              <Input.Password />
            </Form.Item>
            <Form.Item
              name="confirm"
              label="Xác nhận mật khẩu"
              dependencies={["password"]}
              hasFeedback
              rules={[
                {
                  required: true,
                  message: "Vui lòng xác nhận mật khẩu!",
                },
                ({ getFieldValue }) => ({
                  validator(_, value) {
                    if (!value || getFieldValue("password") === value) {
                      return Promise.resolve();
                    }
                    return Promise.reject(new Error("Mật khẩu không khớp"));
                  },
                }),
              ]}
            >
              <Input.Password />
            </Form.Item>

            <Form.Item>
              <div className="flex justify-center">
                <Button type="primary" htmlType="submit">
                  Đăng kí
                </Button>
              </div>
            </Form.Item>
            <div>
              <span>Bạn đã có tài khoản?</span>
              <Link to="/login">Đăng nhập</Link>
            </div>
          </Form>
        </div>
      </Col>
    </Row>
  );
};

export default Register;
