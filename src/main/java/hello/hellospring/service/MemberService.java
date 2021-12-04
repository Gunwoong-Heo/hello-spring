package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional  // JPA를 쓸때는 항상 있어야 함. (select 시에는 필요 X, 여기서는 join메소드에만 걸어도 됨.)
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 회원가입 */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /** 전체회원 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /** id로 회원 조회 */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}