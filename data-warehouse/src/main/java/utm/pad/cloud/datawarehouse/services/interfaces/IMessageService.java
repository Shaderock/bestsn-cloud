package utm.pad.cloud.datawarehouse.services.interfaces;

import utm.pad.cloud.datawarehouse.dtos.MessageDTO;
import utm.pad.cloud.datawarehouse.exceptions.InconsistentDBException;
import utm.pad.cloud.datawarehouse.exceptions.MessageHistoryNotFoundException;
import utm.pad.cloud.datawarehouse.exceptions.MultiChatsException;
import utm.pad.cloud.datawarehouse.exceptions.UserNotFoundException;
import utm.pad.cloud.datawarehouse.models.MessageHistory;
import utm.pad.cloud.datawarehouse.models.User;

import java.util.List;

public interface IMessageService {
    void sendMessage(User user, int toUser, MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException;

    MessageHistory getMessageHistory(User user, int withUserId)
            throws MultiChatsException, InconsistentDBException, UserNotFoundException, MessageHistoryNotFoundException;

    List<MessageHistory> getChatList(User user);
}
