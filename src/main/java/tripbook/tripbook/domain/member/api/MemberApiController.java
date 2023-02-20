package tripbook.tripbook.domain.member.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tripbook.tripbook.domain.member.dto.SignUpDto;
import tripbook.tripbook.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping(value = "/member/signUp")
    public ResponseEntity<String> signUp(@RequestBody final SignUpDto signUpDto){

        return memberService.isEmailDuplicated(signUpDto.getEmail())
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok().build();
    }
}
