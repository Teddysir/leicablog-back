package leica.blog.repository;

import leica.blog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
     Optional<Member> findByUsername(String username);

    Member findByMemberId(String username);

}
