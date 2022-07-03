package com.kk.liargameapplication.comp;


import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ChatRoom {

    @Builder
    public ChatRoom(String roomId) {
        this.roomId = roomId;
    }

    private String roomId;
//    private Set<WebSocketSession> sessions = new HashSet<>();

//    public void handleMessage(WebSocketSession session, Message message) {
//        if (message.getMessageType().equals(MessageType.OPEN)) {
//            sessions.add(session);
//            message.setContents(message.getSender() + "님이 입장하셨습니다.");
//        }
//        sendMessage(message);
//    }
//
//    private <T> void sendMessage(T message) {
//        sessions.forEach(
//                s -> {
//                    try {
//                        s.sendMessage(new TextMessage(
//                                new ObjectMapper().writeValueAsString(message)
//                        ));
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//        );
//
//    }

}
