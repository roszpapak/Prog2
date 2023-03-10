package com.roszpapak.timereserve.message;

import com.roszpapak.timereserve.DTO.MessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {


    @Autowired
    private MessageRepository messageRepository;

    public Message save(MessageDTO messageDTO) {
        Message message = new Message();
        message.setFromId(messageDTO.getFromId());
        message.setToId(messageDTO.getToId());
        message.setMessageContent(messageDTO.getMessage());
        message.setReceivedDate(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getChatMessages(Long from, Long to) {
        return messageRepository.findAllChatMessages(from, to);
    }
}
