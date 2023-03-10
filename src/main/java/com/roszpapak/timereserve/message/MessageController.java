package com.roszpapak.timereserve.message;

import com.roszpapak.timereserve.DTO.MessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MessageController {


    @Autowired
    private MessageService messageService;


    @MessageMapping("/sendMessage/{channelId}")
    @SendTo("/topic/sendMessage/{channelId}")
    public Message publishMessage(MessageDTO message) {
        return messageService.save(message);
    }

    @GetMapping("/getChat/{from}/{to}")
    public List<Message> getMessages(@PathVariable Long from, @PathVariable Long to) {
        return messageService.getChatMessages(from, to);
    }
}
