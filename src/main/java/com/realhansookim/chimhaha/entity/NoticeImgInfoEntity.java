package com.realhansookim.chimhaha.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notice_img_info")
public class NoticeImgInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nii_seq") private Integer niiSeq;
    @Column(name = "nii_filename") private String niiFilename;
    @Column(name = "nii_uri") private String niiUri;
    // @Column(name = "imi_bmi_seq") private String imiBmiSeq;
    @OneToOne (cascade = CascadeType.ALL)@JoinColumn(name = "nii_ni_seq") NoticeInfoEntity musicInfo;
}
