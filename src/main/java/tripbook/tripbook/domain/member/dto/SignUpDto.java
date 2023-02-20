package tripbook.tripbook.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import tripbook.tripbook.global.enums.Gender;
import tripbook.tripbook.global.enums.MemberRole;

@Getter
@Setter
public class SignUpDto {

    private String email;
    private String pw;
    private String name;
    private Gender gender;
    private MemberRole role;
    private Boolean isEnable;


}
