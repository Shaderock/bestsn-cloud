package utm.pad.cloud.datawarehouse.services.interfaces;


import org.springframework.security.core.userdetails.UserDetailsService;
import utm.pad.cloud.datawarehouse.dtos.UserDTO;
import utm.pad.cloud.datawarehouse.exceptions.FriendAlreadyAddedException;
import utm.pad.cloud.datawarehouse.exceptions.FriendDoesNotExistException;
import utm.pad.cloud.datawarehouse.exceptions.LoginAlreadyExistsException;
import utm.pad.cloud.datawarehouse.exceptions.UserNotFoundException;
import utm.pad.cloud.datawarehouse.models.User;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User create(UserDTO userDTO) throws LoginAlreadyExistsException;

    User update(User user, UserDTO userDTO);

    User getById(int id) throws UserNotFoundException;

    User getByLogin(String login) throws UserNotFoundException;

    List<User> search(String searchQuery);

    List<User> getAllFriends(User user);

    void addFriend(User user, int friendId) throws UserNotFoundException, FriendAlreadyAddedException;

    void removeFriend(User user, int friendId) throws UserNotFoundException, FriendDoesNotExistException;

    List<User> searchFriend(User user, String searchQuery);

    List<User> getAll();
}
