package com.ipn.escom.neuropsi.record.server.controller;

import com.ipn.escom.neuropsi.commons.dto.ResponseDto;
import com.ipn.escom.neuropsi.commons.dto.user.UserPasswordDto;
import com.ipn.escom.neuropsi.record.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/reset/password")
    public ResponseDto<String> createPassword(@RequestBody UserPasswordDto userPasswordDto) {
        try {
            userService.createPassword(userPasswordDto);
            return ResponseDto.<String>builder()
                    .entity("Vaya a la página de inicio para iniciar sesión")
                    .message("Usuario activado")
                    .build();
        } catch (Exception e) {
            return ResponseDto.<String>builder()
                    .message(String.format("Ocurrió un error: %s", e.getMessage()))
                    .build();
        }
    }
}
