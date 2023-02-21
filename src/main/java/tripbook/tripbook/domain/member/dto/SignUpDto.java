package tripbook.tripbook.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tripbook.tripbook.domain.member.entity.Member;
import tripbook.tripbook.global.enums.Gender;
import tripbook.tripbook.global.enums.MemberRole;

@Getter
@NoArgsConstructor
public class SignUpDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Setter
    private String pw;

    @NotBlank
    private String name;

    @NotBlank
    private Gender gender;

    private MemberRole role;


    @Builder
    private SignUpDto(String email, String pw, String name, Gender gender, MemberRole role) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.gender = gender;
        this.role = role;
    }

    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .pw(this.pw)
                .name(this.name)
                .gender(this.gender)
                .role(this.role)
                .build();
    }

}
