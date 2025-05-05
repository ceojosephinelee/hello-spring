package hello.spring.repository;

import hello.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);  //회원을 저장하면 저장된 회원이 반환됨.

    Optional<Member> findById(Long id);  //id로 회원 찾기, Optional은 null이 반환될 가능성이 있으면 감싸주는 역할

    Optional<Member> findByName(String name); //name으로 회원 찾기

    List<Member> findAll();  //지금까지 저장된 모든 회원을 리스트로 반환해줌


    void clearStore();
}
