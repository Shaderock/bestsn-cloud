package utm.pad.cloud.datawarehouse.services.implementations;

import lombok.RequiredArgsConstructor;
import utm.pad.cloud.datawarehouse.models.DatabaseSequence;
import utm.pad.cloud.datawarehouse.repositories.SequenceGeneratorRepository;
import utm.pad.cloud.datawarehouse.services.interfaces.ISequenceGeneratorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorService implements ISequenceGeneratorService {

    private final SequenceGeneratorRepository sequenceGeneratorRepository;

    @Override
    public int generateSequence(String sequenceName) {
        DatabaseSequence databaseSequence = sequenceGeneratorRepository.findById(sequenceName)
                .orElse(new DatabaseSequence(sequenceName, 1));
        int sequence = databaseSequence.getSequence();
        databaseSequence.IncrementSequence();
        sequenceGeneratorRepository.save(databaseSequence);
        return sequence;
    }
}
