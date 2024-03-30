package com.realhansookim.chimhaha.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeInfoVO {
    private String title;
    private String content;
    private LocalDate regDt;
    private Integer niMiSeq;
    private Integer niNiiSeq;
}
