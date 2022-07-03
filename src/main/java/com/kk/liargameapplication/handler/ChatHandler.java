package com.kk.liargameapplication.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.liargameapplication.comp.ChatRoom;
import com.kk.liargameapplication.comp.Message;
import com.kk.liargameapplication.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
//@Component
public class ChatHandler extends TextWebSocketHandler {

    private final ChatService chatService;
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {} ",payload);

        Message msg = new ObjectMapper().readValue(payload, Message.class);
        log.info("Message {} ",msg);
        ChatRoom chatRoom = chatService.findByName(msg.getRoomId());
        log.info("ChatRoom {} ",chatRoom);
//        chatRoom.handleMessage(session,msg);
    }
}
