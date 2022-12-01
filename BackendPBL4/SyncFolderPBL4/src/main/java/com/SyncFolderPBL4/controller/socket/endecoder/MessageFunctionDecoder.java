package com.SyncFolderPBL4.controller.socket.endecoder;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.SyncFolderPBL4.controller.socket.cls.MessageFunction;
import com.google.gson.Gson;

public class MessageFunctionDecoder implements Decoder.Text<MessageFunction> {

    private static Gson gson = new Gson();

    @Override
    public MessageFunction decode(String s) throws DecodeException {
        return gson.fromJson(s, MessageFunction.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}