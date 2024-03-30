package com.realhansookim.chimhaha.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Builder
@Entity
@Table(name = "notice_info")
public class NoticeInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ni_seq") private Integer niSeq;
    @Column(name = "ni_title") private String niTitle;
    @Column(name = "ni_content")  private String niContent;
    @Column(name = "ni_reg_dt")  @ColumnDefault(value = "current_timestamp") private LocalDate niRegDt;
    @Column(name = "ni_mi_seq") private Integer niMiSeq;
    @Column(name = "ni_nii_seq") private Integer niNiiSeq; 
}
