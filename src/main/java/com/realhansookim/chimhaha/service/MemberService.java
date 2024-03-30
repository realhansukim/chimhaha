package com.realhansookim.chimhaha.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.realhansookim.chimhaha.entity.MemberInfoEntity;
import com.realhansookim.chimhaha.repository.MemberInfoRepository;
import com.realhansookim.chimhaha.utils.AESAlgorithm;
import com.realhansookim.chimhaha.vo.LoginAdd;
import com.realhansookim.chimhaha.vo.LoginVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberInfoRepository memberInfoRepository;

public LoginVO addLogin(LoginAdd data){
    if(memberInfoRepository.countByMiId(data.getId())==1){
        LoginVO response = LoginVO.builder()
        .code(HttpStatus.BAD_REQUEST)
        .message(data.getId()+"은/는 이미 등록된 아이디 입니다.")
        .status(false)
        .build();
        return response;
    }
    try{
        String encPwd = AESAlgorithm.Encrypt(data.getPwd());
        data.setPwd(encPwd);
    }
    catch(Exception e){
        e.printStackTrace();
    }
    MemberInfoEntity entity = MemberInfoEntity.builder()
    .miId(data.getId()).miPwd(data.getPwd()).miName(data.getName()).miBirth(data.getBirth()).build();
    memberInfoRepository.save(entity);
    return LoginVO.builder().code(HttpStatus.OK).message("저장되었습니다.").status(true).build();
}
}
