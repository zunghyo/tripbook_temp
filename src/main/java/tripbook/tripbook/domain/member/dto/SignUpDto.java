package tripbook.tripbook.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "회원가입 요청 DTO")
public class SignUpDto {

    @Email
    @NotBlank
    @Schema(description = "이메일")
    private String email;

    @NotBlank
    @Setter
    @Schema(description = "비밀번호")
    private String pw;

    @NotBlank
    @Schema(description = "이름")
    private String name;

    @NotBlank
    @Schema(description = "성별")
    private Gender gender;

    @Schema(description = "권한", defaultValue = "ROLE_USER")
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
