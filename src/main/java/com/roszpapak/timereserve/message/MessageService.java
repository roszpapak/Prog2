package com.roszpapak.timereserve.message;

import com.roszpapak.timereserve.DTO.MessageDTO;
import com.roszpapak.timereserve.DTO.UnreadMessageDTO;
import com.roszpapak.timereserve.user.User;
import com.roszpapak.timereserve.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {


    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

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

    public void setMessageSeen(Long id) {
        if (messageRepository.findById(id).isPresent()) {
            Message message = messageRepository.findById(id).get();
            message.setSeen(true);
            messageRepository.save(message);

        }
    }

    public void setMessagesSeen(Long fromId, Long toId) {
        List<Message> messages = messageRepository.findByFromIdAndToId(fromId, toId);
        for (var message : messages) {
            Message ms = messageRepository.findById(message.getId()).get();
            ms.setSeen(true);
            messageRepository.save(ms);
        }
    }

    public List<UnreadMessageDTO> getUnseenMessages(Long id) {
        List<UnreadMessageDTO> ret = new ArrayList<>();
        List<Long> fromIdList = messageRepository.findByToIdAndSeen(id);
        for (Long fromId : fromIdList) {
            User user = userRepository.findById(fromId).get();
            ret.add(new UnreadMessageDTO(user.getId(), user.getFirstName() + " " + user.getLastName()));
        }
        return ret;
    }
}
