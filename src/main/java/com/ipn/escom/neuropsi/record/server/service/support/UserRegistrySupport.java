package com.ipn.escom.neuropsi.record.server.service.support;

import com.ipn.escom.neuropsi.commons.entity.User;
import com.ipn.escom.neuropsi.commons.entity.values.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@AllArgsConstructor
public class UserRegistrySupport {

    private final PasswordEncoder passwordEncoder;

    public User processNewUser(User user, Role role) {
        @NotBlank String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(false);
        user.setRole(role);
        return user;
    }

}