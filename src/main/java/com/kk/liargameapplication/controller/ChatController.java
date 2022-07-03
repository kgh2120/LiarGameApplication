package com.kk.liargameapplication.controller;


import com.kk.liargameapplication.comp.Message;
import com.kk.liargameapplication.comp.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/")
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessageSendingOperations sendingOperations;


    @MessageMapping("/message")
    public void message(Message message) {
        log.info("[GET] message {} ", message);
        if (MessageType.OPEN.equals(message.getMessageType())) {
            message.setContents(String.format("%s 님이 연결되었습니다.", message.getSender()));
            message.setSender("[시스템]");
        }
        sendingOperations.convertAndSend("/sub/room/"+message.getRoomId(),message);
    }





}
