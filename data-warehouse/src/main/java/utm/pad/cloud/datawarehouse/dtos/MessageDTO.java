package utm.pad.cloud.datawarehouse.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utm.pad.cloud.datawarehouse.models.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String text;

    public Message toMessage(boolean straightDirection) {
        Message message = new Message(this.text, straightDirection);
        message.setDateTime();
        return message;
    }
}
