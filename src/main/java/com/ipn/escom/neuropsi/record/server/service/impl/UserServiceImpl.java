package com.ipn.escom.neuropsi.record.server.service.impl;

import com.ipn.escom.neuropsi.commons.dto.user.UserPasswordDto;
import com.ipn.escom.neuropsi.commons.entity.User;
import com.ipn.escom.neuropsi.commons.exception.InvalidPasswordReset;
import com.ipn.escom.neuropsi.commons.exception.UserNotFoundException;
import com.ipn.escom.neuropsi.record.server.repository.UserRepository;
import com.ipn.escom.neuropsi.record.server.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createPassword(UserPasswordDto userPasswordDto) throws UserNotFoundException, InvalidPasswordReset {
        User user = userRepository.findByUsername(userPasswordDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("El usuario solicitado no existe"));
        @NotBlank String password = user.getPassword();
        if (!password.equals(userPasswordDto.getPassword())) {
            throw new InvalidPasswordReset("Credenciales no válidas");
        }
        LOGGER.info("Activando credenciales para {}", userPasswordDto.getUsername());
        String encode = passwordEncoder.encode(userPasswordDto.getNewPassword());
        user.setPassword(encode);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
