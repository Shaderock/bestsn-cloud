package utm.pad.cloud.datawarehouse.models.responses;

import lombok.Data;
import utm.pad.cloud.datawarehouse.models.Message;
import utm.pad.cloud.datawarehouse.models.MessageHistory;

import java.util.List;

@Data
public class MessageHistoryResponse {
    private int withUserId;

    private List<Message> messages;

    public MessageHistoryResponse(MessageHistory messageHistory, int withUserId) {
        this.withUserId = withUserId;
        this.messages = messageHistory.getMessages();
    }
}
