package utm.pad.cloud.datawarehouse.repositories;

import utm.pad.cloud.datawarehouse.models.MessageHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MessageHistoryRepository extends MongoRepository<MessageHistory, String> {

    List<MessageHistory> findByUsersId(int userId);

    Optional<MessageHistory> findById(int id);
}
