package com.sibelsapmaz.library.service;

import com.sibelsapmaz.library.entity.Member;
import com.sibelsapmaz.library.repository.MemberRepository;
import com.sibelsapmaz.library.request.CreateMemberRequest;
import com.sibelsapmaz.library.request.UpdateMemberRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public void createMember(CreateMemberRequest request) {
        Member member = convertToMember(request);

        memberRepository.save(member);
    }

    private Member convertToMember(CreateMemberRequest request) {
        Member member = new Member();
        member.setAddress(request.getAddress());
        member.setMail(request.getMail());
        member.setName(request.getName());
        member.setSurname(request.getSurname());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setTckn(request.getTckn());
        return member;
    }

    public Member updateMember(UpdateMemberRequest request) {
        Member member = checkAndConvertToMember(request);

        return memberRepository.save(member);
    }

    private Member checkAndConvertToMember(UpdateMemberRequest request) {
        Member member = memberRepository.findById(request.getId()).get();

        if (Objects.nonNull(request.getAddress())) {
            member.setAddress(request.getAddress());
        }
        if (Objects.nonNull(request.getMail())) {
            member.setMail(request.getMail());
        }
        if (Objects.nonNull(request.getPhoneNumber())) {
            member.setPhoneNumber(request.getPhoneNumber());
        }
        return member;
    }
}
