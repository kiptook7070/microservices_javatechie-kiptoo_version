package eclectics.io.authservice.utils.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class EntityResponse<T> {
    private String message;
    private T entity;
    private Integer statusCode;
}
