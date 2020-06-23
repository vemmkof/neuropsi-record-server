package com.ipn.escom.neuropsi.record.server.controller;

import com.ipn.escom.neuropsi.commons.dto.ResponseDto;
import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Specialist;
import com.ipn.escom.neuropsi.record.server.service.AdminService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    @PostMapping("/specialist")
    public ResponseDto<Specialist> saveSpecialist(@RequestBody SpecialistRegistryDto registryDto) {
        try {
            Specialist specialist = adminService.saveSpecialist(registryDto);
            return ResponseDto.<Specialist>builder()
                    .entity(specialist).message("Especialista creado.").build();
        } catch (Exception e) {
            LOGGER.error("Error {}", e.getMessage());
            return ResponseDto.<Specialist>builder()
                    .message(String.format("Se produjo un error: %s", e.getMessage())).build();
        }
    }
}
