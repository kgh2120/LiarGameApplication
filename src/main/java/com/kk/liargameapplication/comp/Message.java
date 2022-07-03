package com.kk.liargameapplication.comp;

import lombok.Data;

@Data
public class Message {


    private MessageType messageType;
    private String roomId;
    private String sender;
    private String contents;
}
