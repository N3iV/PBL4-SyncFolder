package com.SyncFolderPBL4.controller.socket.endecoder;

import java.time.LocalDateTime;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.SyncFolderPBL4.config.LocalDateTimeAdapter;
import com.SyncFolderPBL4.controller.socket.cls.MessageReply;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageReplyEncoder implements Encoder.Text<MessageReply> {

    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();;

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
