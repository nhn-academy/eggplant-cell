package com.nhnacademy.eggplantcell.service.impl;

import com.nhnacademy.eggplantcell.dto.request.MemberCreateRequestDto;
import com.nhnacademy.eggplantcell.dto.response.MemberCreateResponseDto;
import com.nhnacademy.eggplantcell.dto.response.MemberResponseDto;
import com.nhnacademy.eggplantcell.entity.Member;
import com.nhnacademy.eggplantcell.error.MemberNotFoundException;
import com.nhnacademy.eggplantcell.repository.MemberRepository;
import com.nhnacademy.eggplantcell.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 멤버 서비스 구현체입니다.
 *
 * @author : 김보민
 * @see MemberService
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberCreateResponseDto addMember(MemberCreateRequestDto createRequest) {
        return new MemberCreateResponseDto(memberRepository.save(Member.builder()
                .email(createRequest.getEmail())
                .redirectUrl(createRequest.getRedirectUrl())
                .build()).getMemberNo());
    }

    @Override
    public MemberResponseDto findMember(String email) {
        return MemberResponseDto.entityToDto(memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new));
    }

    @Override
    public void removeMember(String email) {
        memberRepository.deleteByEmail(email);
    }
}
