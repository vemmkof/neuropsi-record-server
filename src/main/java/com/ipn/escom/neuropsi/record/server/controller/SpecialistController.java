package com.ipn.escom.neuropsi.record.server.controller;

import com.ipn.escom.neuropsi.commons.dto.ResponseDto;
import com.ipn.escom.neuropsi.commons.dto.specialist.PatientRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Patient;
import com.ipn.escom.neuropsi.record.server.service.SpecialistService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specialist")
@AllArgsConstructor
public class SpecialistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialistController.class);
    private static final String ERROR_MSG = "Se produjo un error: %s";
    private static final String ERROR = "Error {}";
    private final SpecialistService specialistService;


    @PostMapping("/patient")
    public ResponseDto<Patient> savePatient(@RequestBody PatientRegistryDto registryDto) {
        try {
            Patient patient = specialistService.savePatient(registryDto);
            return ResponseDto.<Patient>builder()
                    .entity(patient).message("Paciente creado.").build();
        } catch (Exception e) {
            LOGGER.error(ERROR, e.getMessage());
            return ResponseDto.<Patient>builder()
                    .message(String.format(ERROR_MSG, e.getMessage())).build();
        }
    }
}
