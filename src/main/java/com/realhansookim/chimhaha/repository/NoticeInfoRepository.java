package com.realhansookim.chimhaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.realhansookim.chimhaha.entity.NoticeInfoEntity;

public interface NoticeInfoRepository extends JpaRepository <NoticeInfoEntity, Long> {
    
}
