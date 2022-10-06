package com.sibelsapmaz.library.controller;

import com.sibelsapmaz.library.entity.Member;
import com.sibelsapmaz.library.request.CreateMemberRequest;
import com.sibelsapmaz.library.request.UpdateMemberRequest;
import com.sibelsapmaz.library.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/library/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createMember(@RequestBody CreateMemberRequest request) {
        memberService.createMember(request);
    }

    @PutMapping
    public ResponseEntity<Member> updateMember(@RequestBody UpdateMemberRequest request) {
        Member member = memberService.updateMember(request);
        return ResponseEntity.ok(member);
    }
}
