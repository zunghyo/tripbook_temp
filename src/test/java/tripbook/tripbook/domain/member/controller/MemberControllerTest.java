package tripbook.tripbook.domain.member.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tripbook.tripbook.domain.member.dto.SignUpDto;
import tripbook.tripbook.domain.member.service.MemberService;
import tripbook.tripbook.global.enums.Gender;
import tripbook.tripbook.global.enums.MemberRole;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void signUpSuccess() throws Exception {

        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final SignUpDto signUpDto = createSignUpDto("test@gmail.com", "tester", "pwtest", Gender.GENDER_FEMALE, MemberRole.ROLE_USER);
        signUpDto.setPw(encoder.encode(signUpDto.getPw()));

        doReturn(signUpDto.toEntity()).when(memberService).signUp(any(SignUpDto.class));

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/member/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(signUpDto))
        );

        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 가입 실패")
    void signUpFail() throws Exception {

        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final SignUpDto signUpDto = createSignUpDto("test@gmail.com", "tester", "pwtest", Gender.GENDER_FEMALE, MemberRole.ROLE_USER);
        signUpDto.setPw(encoder.encode(signUpDto.getPw()));

        doReturn(true).when(memberService).isEmailDuplicated(signUpDto.getEmail());

        final ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/member/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(signUpDto))
        );

        resultActions.andExpect(status().isBadRequest());
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