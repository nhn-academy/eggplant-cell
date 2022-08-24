package com.nhnacademy.eggplantcell.dto.response;

import com.nhnacademy.eggplantcell.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버 응답 객체입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Integer memberNo;
    private String email;
    private String redirectUrl;

    public static MemberResponseDto entityToDto(Member member) {
        return new MemberResponseDto(member.getMemberNo(), member.getEmail(),
                member.getRedirectUrl());
    }
}
