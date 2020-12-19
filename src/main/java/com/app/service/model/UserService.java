package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.dto.get.GetPackDto;
import com.app.model.dto.get.GetUserAdminDto;
import com.app.model.dto.get.GetUserDto;
import com.app.model.dto.get.GetUserUserDto;
import com.app.model.enums.Role;
import com.app.model.User;
import com.app.model.dto.security.ForgetPasswordDto;
import com.app.model.dto.security.NewPasswordDto;
import com.app.model.security.VerificationToken;
import com.app.model.dto.create.CreateUserDto;
import com.app.repository.UserRepository;
import com.app.repository.VerificationTokenRepository;
import com.app.service.EmailService;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;

    public User registerUser(CreateUserDto createUserDto, HttpServletRequest request) {

        if (createUserDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "USER OBJECT IS NULL");
        }

        if (userRepository.findByUsername(createUserDto.getUsername()).isPresent()) {
            throw new MyException(ExceptionCode.SERVICE, "USERNAME ALREADY EXISTS");
        }

        if (!Objects.equals(createUserDto.getPassword(), createUserDto.getPasswordConfirmation())) {
            throw new MyException(ExceptionCode.SERVICE, "PASSWORD AND CONFIRMED PASSWORD ARE NOT THE SAME");
        }

        if (createUserDto.getRole() == null) {
            createUserDto.setRole(Role.USER);
        }
        if (createUserDto.getEnabled() == null) {
            createUserDto.setEnabled(false);
        }
        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        if (request != null) {
            VerificationToken verificationToken = createTokenVerification(MapperModel.fromCreateUserDtoToUser(createUserDto));
            emailService.sendEmail(verificationToken, request, "activateUser");

            return verificationToken.getUser();
        }

        return userRepository.save(MapperModel.fromCreateUserDtoToUser(createUserDto));
    }

    public VerificationToken createTokenVerification(User user) {

        if (user == null) {
            throw new MyException(ExceptionCode.SERVICE, "USER IS NULL");
        }

        String token = UUID.randomUUID().toString();
        return verificationTokenRepository.save(VerificationToken.builder()
                .token(token)
                .user(user)
                .expirationDateTime(LocalDateTime.now().plusMinutes(60))
                .build());
    }

    public void activateUser(String token) {

        if (token == null) {
            throw new MyException(ExceptionCode.SERVICE, "TOKEN IS NULL");
        }

        VerificationToken verificationToken
                = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "NO USER CONNECTED WITH TOKEN " + token));

        if (verificationToken.getExpirationDateTime().isBefore(LocalDateTime.now())) {
            verificationTokenRepository.delete(verificationToken);
            throw new MyException(ExceptionCode.SERVICE, "TOKEN HAS BEEN EXPIRED");
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        verificationTokenRepository.delete(verificationToken);
    }

    public void generateTokenResetPassword(ForgetPasswordDto forgetPasswordDto, HttpServletRequest request) {
        User user = userRepository
                .findByEmail(forgetPasswordDto.getEmail())
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Email is not correct"));
        VerificationToken verificationToken = createTokenVerification(user);
        emailService.sendEmail(verificationToken, request, "resetPassword");
    }

    public NewPasswordDto getUserWithToken (String token) {
        VerificationToken verificationToken = verificationTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "No user with token " + token));
        return NewPasswordDto.builder().email(verificationToken.getUser().getEmail()).build();
    }

    public void changePassword(NewPasswordDto newPasswordDto) {
        User user = userRepository
                .findByEmail(newPasswordDto.getEmail())
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "No user with email " + newPasswordDto.getEmail()));
        if (!newPasswordDto.getNewPassword().equals(newPasswordDto.getConfirmNewPassword())) {
            throw new MyException(ExceptionCode.SERVICE, "Passwords are not correct");
        }

        user.setPassword(passwordEncoder.encode(newPasswordDto.getNewPassword()));
        System.out.println(userRepository.save(user));
    }

    public List<GetUserUserDto> getAllUser() {
        return userRepository
                .findAll()
                .stream()
                .filter(x->x.getRole()==Role.USER)
                .map(MapperModel::fromUserToGetUserUserDto)
                .collect(Collectors.toList());
    }

    public List<GetUserAdminDto> getAllAdmin() {
        return userRepository
                .findAll()
                .stream()
                .filter(x->x.getRole()==Role.ADMIN)
                .map(MapperModel::fromUserToGetUserAdminDto)
                .collect(Collectors.toList());
    }
}