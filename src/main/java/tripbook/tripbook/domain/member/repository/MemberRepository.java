package tripbook.tripbook.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tripbook.tripbook.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
}
