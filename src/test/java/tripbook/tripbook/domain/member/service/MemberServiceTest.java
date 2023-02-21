package tripbook.tripbook.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import tripbook.tripbook.domain.member.dto.SignUpDto;
import tripbook.tripbook.domain.member.entity.Member;
import tripbook.tripbook.global.enums.Gender;
import tripbook.tripbook.global.enums.MemberRole;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired private MemberService memberService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @DisplayName("회원 가입 성공 테스트")
    void signUpSuccess(){

        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final SignUpDto signUpDto = createSignUpDto("test@gmail.com", "tester", "pwtest", Gender.GENDER_FEMALE, MemberRole.ROLE_USER);

        final Member savedUser = memberService.signUp(signUpDto);

        assertThat(savedUser.getEmail()).isEqualTo(signUpDto.getEmail());
        assertThat(savedUser.getName()).isEqualTo(signUpDto.getName());

    }

    @Test
    @DisplayName("이메일 중복 체크 테스트")
    void checkEmailDuplicated(){

        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final SignUpDto signUpDto = createSignUpDto("test@gmail.com", "tester", "pwtest", Gender.GENDER_FEMALE, MemberRole.ROLE_USER);
        final Member savedUser = memberService.signUp(signUpDto);

        final boolean isEmailDuplicated = memberService.isEmailDuplicated("test@gmail.com");

        assertThat(isEmailDuplicated).isEqualTo(true);

    }

    private SignUpDto createSignUpDto(String email, String name, String pw, Gender gender, MemberRole role) {
        final SignUpDto signUpDto = SignUpDto.builder()
                .email(email)
                .name(name)
                .pw(pw)
                .gender(gender)
                .role(role)
                .build();
        return signUpDto;
    }

}