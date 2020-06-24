package com.ipn.escom.neuropsi.record.server.controller;

import com.ipn.escom.neuropsi.commons.dto.ResponseDto;
import com.ipn.escom.neuropsi.commons.dto.admin.InstituteRegistryDto;
import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Institute;
import com.ipn.escom.neuropsi.commons.entity.Specialist;
import com.ipn.escom.neuropsi.commons.entity.values.Role;
import com.ipn.escom.neuropsi.record.server.service.AdminService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;
    private static final String ERROR = "Error {}";
    private static final String ERROR_MSG = "Se produjo un error: %s";

    @PostMapping("")
    public ResponseDto<Specialist> saveAdmin(@RequestBody SpecialistRegistryDto registryDto) {
        try {
            Specialist specialist = adminService.saveSpecialist(registryDto, Role.ADMINISTRATOR);
            return ResponseDto.<Specialist>builder()
                    .entity(specialist).message("Administrador creado.").build();
        } catch (Exception e) {
            LOGGER.error(ERROR, e.getMessage());
            return ResponseDto.<Specialist>builder()
                    .message(String.format(ERROR_MSG, e.getMessage())).build();
        }
    }

    @PostMapping("/specialist")
    public ResponseDto<Specialist> saveSpecialist(@RequestBody SpecialistRegistryDto registryDto) {
        try {
            Specialist specialist = adminService.saveSpecialist(registryDto, Role.SPECIALIST);
            return ResponseDto.<Specialist>builder()
                    .entity(specialist).message("Especialista creado.").build();
        } catch (Exception e) {
            LOGGER.error(ERROR, e.getMessage());
            return ResponseDto.<Specialist>builder()
                    .message(String.format(ERROR_MSG, e.getMessage())).build();
        }
    }

    @PostMapping("/institute")
    public ResponseDto<Institute> saveInstitute(@RequestBody InstituteRegistryDto registryDto) {
        try {
            Institute institute = adminService.saveInstitute(registryDto);
            return ResponseDto.<Institute>builder()
                    .entity(institute).message("Especialista creado.").build();
        } catch (Exception e) {
            LOGGER.error(ERROR, e.getMessage());
            return ResponseDto.<Institute>builder()
                    .message(String.format(ERROR_MSG, e.getMessage())).build();
        }
    }

    @GetMapping("/institute")
    public ResponseDto<ArrayList<Institute>> getInstitutes() {
        try {
            ArrayList<Institute> institutes = adminService.getInstitutes();
            return ResponseDto.<ArrayList<Institute>>builder()
                    .entity(institutes).message("Institutos encontrados.").build();
        } catch (Exception e) {
            LOGGER.error(ERROR, e.getMessage());
            return ResponseDto.<ArrayList<Institute>>builder()
                    .message(String.format(ERROR_MSG, e.getMessage())).build();
        }
    }

}
