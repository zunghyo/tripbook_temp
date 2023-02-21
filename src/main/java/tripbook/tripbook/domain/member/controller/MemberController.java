package tripbook.tripbook.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tripbook.tripbook.domain.member.dto.SignUpDto;
import tripbook.tripbook.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member/signUp")
    public ResponseEntity<String> signUp(@RequestBody final SignUpDto signUpDto){

        if(memberService.isEmailDuplicated(signUpDto.getEmail())) {
            return ResponseEntity.badRequest().build();
        }
        memberService.signUp(signUpDto);

        return ResponseEntity.ok().build();
    }
}
