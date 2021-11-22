package com.roszpapak.timereserve.registration;

import com.roszpapak.timereserve.business.Business;
import com.roszpapak.timereserve.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final Business business;
    private final UserRole userRole;

}
