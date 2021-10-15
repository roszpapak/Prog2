package com.roszpapak.timereserve.user;

import com.roszpapak.timereserve.registration.token.ConfirmationToken;
import com.roszpapak.timereserve.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email %s not found",email)));
    }

    public String signUpUser(User user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) throw new IllegalStateException("email already taken");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user

        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: SEND EMAIL

        return token;
    }

    public void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
}
