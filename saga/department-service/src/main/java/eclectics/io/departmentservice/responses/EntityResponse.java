package eclectics.io.departmentservice.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntityResponse<T> {
    private String message;
    private T entity;
    private Integer statusCode;
}
