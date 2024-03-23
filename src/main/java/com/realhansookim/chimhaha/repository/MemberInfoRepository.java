package com.realhansookim.chimhaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realhansookim.chimhaha.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity,Integer> {
    
}
