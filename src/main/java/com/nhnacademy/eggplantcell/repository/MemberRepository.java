package com.nhnacademy.eggplantcell.repository;

import com.nhnacademy.eggplantcell.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 멤버 jpa 레퍼지토리입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
    void deleteByEmail(String email);
}
