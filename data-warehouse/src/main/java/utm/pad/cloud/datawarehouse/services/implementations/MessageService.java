package utm.pad.cloud.datawarehouse.services.implementations;

import lombok.RequiredArgsConstructor;
import utm.pad.cloud.datawarehouse.dtos.MessageDTO;
import utm.pad.cloud.datawarehouse.exceptions.InconsistentDBException;
import utm.pad.cloud.datawarehouse.exceptions.MessageHistoryNotFoundException;
import utm.pad.cloud.datawarehouse.exceptions.MultiChatsException;
import utm.pad.cloud.datawarehouse.exceptions.UserNotFoundException;
import utm.pad.cloud.datawarehouse.models.MessageHistory;
import utm.pad.cloud.datawarehouse.models.User;
import utm.pad.cloud.datawarehouse.repositories.MessageHistoryRepository;
import utm.pad.cloud.datawarehouse.services.interfaces.IMessageService;
import utm.pad.cloud.datawarehouse.services.interfaces.ISequenceGeneratorService;
import utm.pad.cloud.datawarehouse.services.interfaces.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    private final IUserService userService;
    private final ISequenceGeneratorService sequenceGeneratorService;
    private final MessageHistoryRepository messageHistoryRepository;

    @Override
    @Transactional
    public void sendMessage(User sender, int receiverId, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException {

        MessageHistory messageHistory;
        try {
            messageHistory = getMessageHistory(sender, receiverId);
        } catch (MessageHistoryNotFoundException e) {
            User receiver = userService.getById(receiverId);
            messageHistory = new MessageHistory(
                    sequenceGeneratorService.generateSequence(MessageHistory.SEQUENCE_NAME),
                    sender, receiver);
        }

        messageHistory.addMessage(messageDTO.toMessage(!messageHistory.getUsersId().toArray()[0].equals(receiverId)));
        messageHistoryRepository.save(messageHistory);
    }

    @Override
    public MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException, MessageHistoryNotFoundException {
        User withUser = userService.getById(withUserId);
        List<MessageHistory> messageHistoryUserList = messageHistoryRepository
                .findByUsersId(user.getId());

        List<MessageHistory> messageHistoryWithUserList = messageHistoryRepository
                .findByUsersId(withUser.getId());

        messageHistoryUserList.retainAll(messageHistoryWithUserList);

        if (messageHistoryUserList.size() == 0) {
            throw new MessageHistoryNotFoundException("Message history with user with id = " + withUserId +
                    " not found");
        }
        if (messageHistoryUserList.size() != 1) {
            throw new MultiChatsException("Multiple chats for users with id = [" + user.getId() +
                    ", " + withUserId + "] has found");
        }

        return messageHistoryRepository
                .findById(messageHistoryUserList.get(0).getId())
                .orElseThrow(() ->
                        new InconsistentDBException("DB has changed during transaction " +
                                "(not found MessageHistory that was present)"));
    }

    @Override
    public List<MessageHistory> getChatList(User user) {
        return messageHistoryRepository.findByUsersId(user.getId());
    }
}
