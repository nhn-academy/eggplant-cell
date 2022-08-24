package com.nhnacademy.eggplantcell.error;

/**
 * 멤버를 찾을 수 없을 때 던질 예외입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
public class MemberNotFoundException extends RuntimeException{
    public static final String MESSAGE = "멤버를 찾을 수 없습니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
