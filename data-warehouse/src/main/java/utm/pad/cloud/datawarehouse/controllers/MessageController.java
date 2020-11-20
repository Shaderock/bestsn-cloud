package utm.pad.cloud.datawarehouse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utm.pad.cloud.datawarehouse.dtos.MessageDTO;
import utm.pad.cloud.datawarehouse.exceptions.InconsistentDBException;
import utm.pad.cloud.datawarehouse.exceptions.MessageHistoryNotFoundException;
import utm.pad.cloud.datawarehouse.exceptions.MultiChatsException;
import utm.pad.cloud.datawarehouse.exceptions.UserNotFoundException;
import utm.pad.cloud.datawarehouse.models.User;
import utm.pad.cloud.datawarehouse.models.responses.ChatListResponse;
import utm.pad.cloud.datawarehouse.models.responses.MessageHistoryResponse;
import utm.pad.cloud.datawarehouse.models.responses.Response;
import utm.pad.cloud.datawarehouse.services.interfaces.IMessageService;
import utm.pad.cloud.datawarehouse.utils.AuthenticationHelper;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/message", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class MessageController{
    private final IMessageService messageService;

    @PostMapping(value = "/send")
    public ResponseEntity<Response<String>> sendMessage(@RequestParam(name = "to") int receiverId,
                                         @RequestBody @Valid MessageDTO messageDTO)
            throws UserNotFoundException, MultiChatsException, InconsistentDBException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        messageService.sendMessage(user, receiverId, messageDTO);
        return ResponseEntity.ok(new Response<>("Message has been sent"));
    }

    @GetMapping(value = "/history")
    public ResponseEntity<MessageHistoryResponse> getMessageHistory(@RequestParam int withUserId)
            throws UserNotFoundException, MultiChatsException,
            InconsistentDBException, MessageHistoryNotFoundException {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(
                new MessageHistoryResponse(messageService.getMessageHistory(user, withUserId), withUserId));
    }

    @GetMapping(value = "/chatlist")
    public ResponseEntity<ChatListResponse> getChatList() {
        User user = AuthenticationHelper.getAuthenticatedUser();
        return ResponseEntity.ok(new ChatListResponse(messageService.getChatList(user), user));
    }
}
