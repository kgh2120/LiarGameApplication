package com.kk.liargameapplication.controller;



import com.kk.liargameapplication.comp.ChatRoom;
import com.kk.liargameapplication.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ChatRoomController {

    private final ChatService chatService;


    @GetMapping("/")
    public String home(Model model) {
        List<ChatRoom> rooms = chatService.findAll();
        model.addAttribute("rooms", rooms);
        return "index";
    }

    @GetMapping("/room/{roomId}")
    public String goToChatRoom(@PathVariable("roomId") String roomId, Model model) {
        log.info("ID = {} ", roomId);
        model.addAttribute("roomId",roomId);

        return "chatRoom";
    }




    @PostMapping("/addRoom")
    public String create(@RequestParam String roomId) {
        log.info("[CREATED] roomID = {} ", roomId);
        chatService.createChatRoom(roomId);
        return "redirect:/";
    }

//    @GetMapping("/{roomId}")
//    public ResponseEntity<ChatRoom> findRoom(@PathVariable("roomId") String roomId) {
//        ChatRoom chatRoom = chatService.findByName(roomId);
//        return ResponseEntity.status(HttpStatus.OK).body(chatRoom);
//    }
}
