package utm.pad.cloud.datawarehouse.models.responses;

import lombok.Data;
import utm.pad.cloud.datawarehouse.models.MessageHistory;
import utm.pad.cloud.datawarehouse.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ChatListResponse {
    private List<Integer> withUserIds;

    public ChatListResponse(List<MessageHistory> messageHistories, User user) {
        Set<Integer> withUserIdsSet = new HashSet<>();
        for (MessageHistory messageHistory : messageHistories) {
            withUserIdsSet.addAll(messageHistory.getUsersId());
        }
        withUserIdsSet.remove(user.getId());
        withUserIds = new ArrayList<>(withUserIdsSet);
    }
}
