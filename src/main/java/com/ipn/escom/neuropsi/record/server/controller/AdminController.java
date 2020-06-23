package com.ipn.escom.neuropsi.record.server.controller;

import com.ipn.escom.neuropsi.commons.dto.ResponseDto;
import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Specialist;
import com.ipn.escom.neuropsi.record.server.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/specialist")
    public ResponseDto<Specialist> saveSpecialist(@RequestBody SpecialistRegistryDto registryDto) {
//        Specialist specialist = adminService.saveSpecialist(registryDto);
        return Optional.ofNullable(adminService.saveSpecialist(registryDto))
                .map(specialist -> ResponseDto.<Specialist>builder().build())
                .orElseGet(() -> ResponseDto.<Specialist>builder().build());
    }
}
