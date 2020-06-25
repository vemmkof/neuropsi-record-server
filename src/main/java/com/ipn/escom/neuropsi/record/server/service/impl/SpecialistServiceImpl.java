package com.ipn.escom.neuropsi.record.server.service.impl;

import com.ipn.escom.neuropsi.commons.dto.specialist.PatientRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.*;
import com.ipn.escom.neuropsi.commons.entity.keys.PatientDiseaseKey;
import com.ipn.escom.neuropsi.commons.entity.keys.PatientSpecialistKey;
import com.ipn.escom.neuropsi.commons.entity.values.Role;
import com.ipn.escom.neuropsi.commons.exception.UserNameNotAvailableException;
import com.ipn.escom.neuropsi.record.server.repository.*;
import com.ipn.escom.neuropsi.record.server.service.SpecialistService;
import com.ipn.escom.neuropsi.record.server.service.support.MailSupport;
import com.ipn.escom.neuropsi.record.server.service.support.UserRegistrySupport;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialistServiceImpl.class);
    private final UserRepository userRepository;
    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;
    private final UserRegistrySupport userRegistrySupport;
    private final PatientDiseaseRepository patientDiseaseRepository;
    private final SpecialistRepository specialistRepository;
    private final PatientSpecialistRepository patientSpecialistRepository;
    private final InstituteRepository instituteRepository;
    private final MailSupport mailSupport;

    @Override
    @Transactional
    public Patient savePatient(PatientRegistryDto registryDto, Principal principal)
            throws UserNameNotAvailableException, TemplateException, IOException, MessagingException {
        Patient patient = registryDto.getPatient();
        @NotNull User user = patient.getUser();
        if (userRepository.findByUsername(user.getUsername()).orElse(null) != null) {
            throw new UserNameNotAvailableException("Nombre de usuario no disponible");
        }
        LOGGER.info("paciente: {}", patient);
        LOGGER.info("validar enfermedades");
        validateDiseases(registryDto.getDiseases());
        user = userRegistrySupport.processNewUser(user, Role.PATIENT);
        user = userRepository.save(user);
        LOGGER.info("user: {}", user);
        sendMail(user);
        patient.setUser(user);
        patient = patientRepository.save(patient);
        Patient finalPatient = patient;
        LOGGER.info("finalpaciente: {}", finalPatient);
        registryDto.getDiseases().forEach(disease -> {
            patientDiseaseRepository.save(
                    PatientDisease.builder()
                            .disease(disease).patient(finalPatient)
                            .patientDiseaseKey(PatientDiseaseKey.builder()
                                    .idDisease(disease.getIdDisease())
                                    .idPatient(finalPatient.getIdPatient())
                                    .build())
                            .build()
            );
        });
        String name = principal.getName();
        LOGGER.info("name {}", name);
        User specialistUser = userRepository.findByUsername(name).orElse(null);
        LOGGER.info("specialistUser {}", specialistUser);
        Specialist specialist = specialistRepository.findByUser(specialistUser)
                .orElse(null);
        if (specialist == null) {
            specialist = specialistRepository.save(
                    Specialist.builder()
                            .institute(instituteRepository.findAll().get(0))
                            .professionalId("N/A")
                            .user(specialistUser)
                            .build()
            );
        }
        PatientSpecialist patientSpecialist = PatientSpecialist.builder()
                .specialist(specialist).patient(patient)
                .patientSpecialistKey(PatientSpecialistKey.builder()
                        .idPatient(patient.getIdPatient())
                        .idSpecialist(specialist.getIdSpecialist())
                        .build())
                .build();
        patientSpecialistRepository.save(patientSpecialist);
        return patient;
    }

    @Override
    public ArrayList<Disease> getDiseases() {
        return new ArrayList<>(diseaseRepository.findAll());
    }

    private void sendMail(User user) throws TemplateException, IOException, MessagingException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("username", user.getUsername());
        parameters.put("hashcode", user.getPassword());
        mailSupport.sendMail(user.getUsername(), MailSupport.NEW_USER,
                MailSupport.NEW_USER_TEMPLATE, parameters);
    }

    private void validateDiseases(List<Disease> diseases) {
        diseases.forEach(disease -> {
            LOGGER.info("disease: {}", disease);
            disease = diseaseRepository.findById(disease.getIdDisease())
                    .orElseThrow(IllegalArgumentException::new);
        });
    }
}
