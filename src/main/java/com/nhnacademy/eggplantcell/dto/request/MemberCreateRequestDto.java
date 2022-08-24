package com.nhnacademy.eggplantcell.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

/**
 * 멤버 정보 추가 요청 dto입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public class MemberCreateRequestDto {
    @NotBlank
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String email;

    @NotBlank
    @URL(message = "url 형식이 맞지 않습니다.")
    private String redirectUrl;
}
