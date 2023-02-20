package tripbook.tripbook.domain.member.entity;


import jakarta.persistence.*;
import lombok.*;
import tripbook.tripbook.global.common.BasicEntity;
import tripbook.tripbook.global.enums.Gender;
import tripbook.tripbook.global.enums.MemberRole;

import java.io.Serializable;

@Entity
@Table(name = "TB_MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BasicEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    private String pw;

    @Setter
    @Column(nullable = false)
    private String name;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(String email, String pw, String name, Gender gender, MemberRole role) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.gender = gender;
        this.role = role;
    }
}