package com.project.moagym.domain;

import com.project.moagym.domain.enums.Address;
import com.project.moagym.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long memberId;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    private String email;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    private String password;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    private String name;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    private String nickName;

    @Embedded
    private Address address;

    @NotBlank(message = "공백을 사용할 수 없으며 반드시 해당 값을 입력하여야 합니다.")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDateTime joinDate;

    private String memberImg;

//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member")
//    private List<Review> reviews = new ArrayList<>();

}
