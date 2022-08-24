package com.nhnacademy.eggplantcell.controller;

import com.nhnacademy.eggplantcell.dto.request.MemberCreateRequestDto;
import com.nhnacademy.eggplantcell.dto.response.MemberCreateResponseDto;
import com.nhnacademy.eggplantcell.dto.response.MemberResponseDto;
import com.nhnacademy.eggplantcell.service.MemberService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 멤버 컨트롤러입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/eggplant-cell")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberCreateResponseDto> memberAdd(
            @Valid @RequestBody MemberCreateRequestDto createRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(memberService.addMember(createRequest));
    }

    @GetMapping("/members/{email}")
    public ResponseEntity<MemberResponseDto> memberDetails(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(memberService.findMember(email));
    }

    @DeleteMapping("/members/{email}")
    public ResponseEntity<Void> memberRemove(@PathVariable String email) {
        memberService.removeMember(email);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce("",
                        (accumulateMsg, nextMessage) -> accumulateMsg + "," + nextMessage)
                .trim());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(map);
    }
}
