package com.nhnacademy.eggplantcell.service;

import com.nhnacademy.eggplantcell.dto.request.MemberCreateRequestDto;
import com.nhnacademy.eggplantcell.dto.response.MemberCreateResponseDto;
import com.nhnacademy.eggplantcell.dto.response.MemberResponseDto;

/**
 * 멤버 서비스 인터페이스입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
public interface MemberService {
    MemberCreateResponseDto addMember(MemberCreateRequestDto createRequest);
    MemberResponseDto findMember(String email);
    void removeMember(String email);
}
