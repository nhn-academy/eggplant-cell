package com.nhnacademy.eggplantcell.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시큐어 키 매니저 응답 dto 입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
@Getter
@NoArgsConstructor
public class SecureKeyResponseDto {
    private Header header;
    private Body body;

    @Getter
    public static class Header {
        private Integer resultCode;
        private String resultMessage;
        private Boolean isSuccessful;
    }

    @Getter
    public static class Body {
        private String secret;
    }
}
