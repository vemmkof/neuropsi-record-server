package com.ipn.escom.neuropsi.record.server.service.impl;

import com.ipn.escom.neuropsi.commons.dto.user.UserPasswordDto;
import com.ipn.escom.neuropsi.commons.entity.User;
import com.ipn.escom.neuropsi.commons.exception.InvalidPasswordReset;
import com.ipn.escom.neuropsi.commons.exception.UserNotFoundException;
import com.ipn.escom.neuropsi.record.server.repository.UserRepository;
import com.ipn.escom.neuropsi.record.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createPassword(UserPasswordDto userPasswordDto) throws UserNotFoundException, InvalidPasswordReset {
        User user = userRepository.findByUsername(userPasswordDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("El usuario solicitado no existe"));
        @NotBlank String password = user.getPassword();
        if (!password.equals(userPasswordDto.getNewPassword())) {
            throw new InvalidPasswordReset("Credenciales no válidas");
        }
        user.setPassword(passwordEncoder.encode(userPasswordDto.getNewPassword()));
        userRepository.save(user);
    }
}
