import React from "react";
import { useEffect } from "react";
import io from "socket.io-client";
const socket = io.connect("http://localhost:9000");

const Test = () => {
  useEffect(async () => {
    const res = await fetch("http://localhost:8080/SyncFolderPBL4/api/hello");
    console.log(res);
    socket.on("message", (data) => {
      console.log(data);
    });
  });
  return <div>Test</div>;
};

export default Test;
