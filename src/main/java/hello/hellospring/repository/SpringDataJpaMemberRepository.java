package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 'JpaRepository<>'를 extends 하면, Spring Data JPA가 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL >> select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}