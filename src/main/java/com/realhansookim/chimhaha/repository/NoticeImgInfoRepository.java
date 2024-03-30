package com.realhansookim.chimhaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realhansookim.chimhaha.entity.NoticeImgInfoEntity;

public interface NoticeImgInfoRepository extends JpaRepository<NoticeImgInfoEntity, Long> {
    NoticeImgInfoEntity findByNiiFilename (String niiFilename);
}
