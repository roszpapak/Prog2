package com.roszpapak.timereserve.user;

import com.roszpapak.timereserve.business.BusinessRepository;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BusinessRepository businessRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }


    //SAVING USER

    public String signUpUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) throw new IllegalStateException("email already taken");

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        if (savedUser.getBusiness() != null) {
            savedUser.getBusiness().setUser(savedUser);
            businessRepository.save(savedUser.getBusiness());
        }


        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user

        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);


        return token;
    }

    public void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    //Get Users by keyword
    public List<User> findByKeyWord(String keyword) {
        return userRepository.findByKeyWord(keyword);
    }

    //Get User by ID
    public User get(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.get();
    }
}
