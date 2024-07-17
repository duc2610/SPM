package com.swp391.SPM.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePassword {
    private String oldPassword;
    private String newPassword;
    private String comfirmPassword;

}
