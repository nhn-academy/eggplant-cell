package com.nhnacademy.eggplantcell.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버 엔티티입니다.
 *
 * @author : 김보민
 * @since 1.0
 */
@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberNo;

    @NotNull
    String email;

    @NotNull
    private String redirectUrl;

    @Builder
    public Member(String email, String redirectUrl) {
        this.email = email;
        this.redirectUrl = redirectUrl;
    }
}
