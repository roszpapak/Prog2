package com.roszpapak.timereserve.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserEditRequest {

    private String firstName;
    private String lastName;
    private String password;

}
