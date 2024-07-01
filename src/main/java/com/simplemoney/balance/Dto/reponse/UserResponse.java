package com.simplemoney.balance.Dto.reponse;

import com.simplemoney.balance.Dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends BaseDto {
    String username;
    String emailId;
}
