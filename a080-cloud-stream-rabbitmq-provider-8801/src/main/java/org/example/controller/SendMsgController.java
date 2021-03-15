package org.example.controller;

import org.example.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMsgController {

    @Autowired
    private MessageProvider messageProvider;
    @GetMapping("sendMsg/{msg}")
    public void send(@PathVariable("msg") String msg) {
         messageProvider.send(msg);
    }

}
