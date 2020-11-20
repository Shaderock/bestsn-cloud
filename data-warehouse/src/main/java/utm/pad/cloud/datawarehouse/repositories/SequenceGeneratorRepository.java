package utm.pad.cloud.datawarehouse.repositories;

import utm.pad.cloud.datawarehouse.models.DatabaseSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SequenceGeneratorRepository extends MongoRepository<DatabaseSequence, String> {

    Optional<DatabaseSequence> findById(String id);
}
