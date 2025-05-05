package hello.spring.service;

import hello.spring.domain.Member;
import hello.spring.repository.MemberRepository;
import hello.spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
//Test 함수 이름은 한글 이름 써도 됨
    MemberService memberService;
    MemberRepository memberRepository;
    //MemberService memberService = new MemberService();
    //MemberRepository memberRepository = new MemoryMemberRepository() ;// 새로 선언할 필요 없어야함->@BeforEach로 생성해주기

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();

    }
    @Test
    void 회원가입() {
        //given
        Member member = new Member();//member 선언
        member.setName("spring1"); //member 이름 설정
        //when
        Long saveId = memberService.join(member); //member 저장 후 Id return

        //then
        Member findMember = memberService.findOne(saveId).get();//저장한 게 repository에 있는지 확인...?????
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");

        Member member2 = new Member();
        member2.setName("spring1");
        //when 중복 되었을 떄
        memberService.join(member1);
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }


        //then



    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}