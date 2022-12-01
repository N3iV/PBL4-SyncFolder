package com.SyncFolderPBL4.controller.socket.endecoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.SyncFolderPBL4.controller.socket.cls.MessageReply;
import com.google.gson.Gson;

public class MessageReplyEncoder implements Encoder.Text<MessageReply> {

    private static Gson gson = new Gson();

    @Override
    public String encode(MessageReply message) throws EncodeException {
        return gson.toJson(message);
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
