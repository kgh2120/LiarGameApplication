package com.kk.liargameapplication.service;


import com.kk.liargameapplication.comp.ChatRoom;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Service
public class ChatService {

    private Map<String, ChatRoom> chatRooms = new HashMap<>();

    public ChatRoom createChatRoom(String name) {
        chatRooms.put(name,ChatRoom.builder().roomId(name).build());
        return chatRooms.get(name);
    }

    public ChatRoom findByName(String name) {
        return chatRooms.get(name);
    }

    public List<ChatRoom> findAll() {
        return new ArrayList<>(chatRooms.values());
    }
}
