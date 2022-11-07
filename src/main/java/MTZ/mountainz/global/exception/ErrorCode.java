package MTZ.mountainz.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {


    //회원가입
    EMAIL_DUPLICATION_409(HttpStatus.CONFLICT, "이미 가입된 회원입니다.");

    //로그인




    private final HttpStatus httpStatus;
    private final String message;
}
