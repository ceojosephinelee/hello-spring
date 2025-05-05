package hello.spring.service;

import hello.spring.domain.Member;
import hello.spring.repository.MemberRepository;
import hello.spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
//service는 repository보다 비즈니스에 좀 더 연관된 이름을 지어야
    private final MemberRepository memberRepository;//repository 가져오기
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //Dependency Injection(DI)라고 함
    }
    /*
    * 회원가입
    */
    public Long join(Member member) {
        //  같은 이름 회원 중복 허용 x
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){ //사실상 findAll()과 동일 기능
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);

    }


}
