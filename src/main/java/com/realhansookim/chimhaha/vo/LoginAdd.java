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
public class LoginAdd {
    private String id;
    private String pwd;
    private String name;
    private LocalDate birth;
}
