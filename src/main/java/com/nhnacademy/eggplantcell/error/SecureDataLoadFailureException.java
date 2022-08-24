package com.nhnacademy.eggplantcell.error;

/**
 * 시크릿 키 매니저를 통한 데이터를 불러올 수 없을 때 던질 예외입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
public class SecureDataLoadFailureException extends RuntimeException{
    public static final String MESSAGE = "시크릿 키 매니저 설정에 실패하였습니다.";

    public SecureDataLoadFailureException() {
        super(MESSAGE);
    }
}
