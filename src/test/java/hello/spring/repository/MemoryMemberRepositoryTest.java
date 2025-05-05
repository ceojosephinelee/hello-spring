package hello.spring.repository;

import hello.spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {      //test니 public 쓸 필요 없음

    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach //test 한 번 끝날 떄마다 실행해주는 것
    public void afterEach() {
        repository.clearStore();

    }

    @Test
    void save() { //회원 저장 후 반환
        //given: 회원 저장
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        //when : 회원 반환
        Member result = repository.findById(member.getId()).get();
        //then : 검증 방법-true or false
        //System.out.println("result="+(result==member) );
        //Assertions.assertEquals(member, result);
        Assertions.assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    void findByName() {  //  회원 이름으로 회원 찾기
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);       //회원 1 저장

        Member member2 = new Member();
        member2.setName("hello");
        repository.save(member2);       //회원 2 저장

        Member result = repository.findByName("spring").get(); //"spring"이라는 이름을 가진 회원 찾기

        Assertions.assertThat(result).isEqualTo(member1); //result == member1 비교
    }

    /* 이거 왜 안되는지 모르겠음.
    @Test
    void findById() { //회원 id로 찹기
        Member member3 = new Member();
        member3.setId(1L);
        repository.save(member3);

        Member member4 = new Member();
        member4.setId(2L);
        repository.save(member4);

        Member result = repository.findById(1L).get();

        Assertions.assertThat(result).isEqualTo(member3);


    }
    */

    @Test
    void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);

    }
    //test 전체 돌리면 에러가 남. 하나 돌릴 땐 에러 안 났는데. 왜? 순서 의존적으로 짜면 x->한 번 테스트 할 때마다 clear 해줘야->afterEach()


}
