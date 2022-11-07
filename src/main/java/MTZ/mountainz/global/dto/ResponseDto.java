package MTZ.mountainz.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean success;
    private T data;
//    private int code;
    private Error error;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(true, data, null);
    }
    public static <T> ResponseDto<T> success(boolean success ,T data) {
        return new ResponseDto<>(false, data, null);
    }

    public static <T> ResponseDto<T> fail(HttpStatus httpStatus, String message) {
        return new ResponseDto<>(false, null, new Error(httpStatus, message));
    }
}
