package utm.pad.cloud.datawarehouse.models.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response<T> {
    private T value;
}
