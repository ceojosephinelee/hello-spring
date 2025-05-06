package hello.spring.controller;

import hello.spring.domain.Member;
import hello.spring.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService; //dependency injection
    }

    @GetMapping("/members/new")  //데이터 조회
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")  //데이터 등록
    public String create(MemberForm form) {  //MemberForm 에 name 넣기
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member: " + member);

        memberService.join(member); //회원 가입

        return "redirect:/";  //회원 가입 끝나면 home 화면으로

    }
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
