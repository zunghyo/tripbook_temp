package tripbook.tripbook.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripbook.tripbook.domain.member.dto.SignUpDto;
import tripbook.tripbook.domain.member.entity.Member;
import tripbook.tripbook.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Member signUp(final SignUpDto signupDto){
        final Member member = Member.builder()
                .email(signupDto.getEmail())
                .pw(bCryptPasswordEncoder.encode(signupDto.getPw()))
                .name(signupDto.getName())
                .gender(signupDto.getGender())
                .role(signupDto.getRole())
                .build();

        return memberRepository.save(member);
    }

    public boolean isEmailDuplicated(final String email) {
        return memberRepository.existsByEmail(email);
    }
}
