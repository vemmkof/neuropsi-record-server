package com.ipn.escom.neuropsi.record.server.service.impl;

import com.ipn.escom.neuropsi.commons.dto.specialist.PatientRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Disease;
import com.ipn.escom.neuropsi.commons.entity.Patient;
import com.ipn.escom.neuropsi.commons.entity.PatientDisease;
import com.ipn.escom.neuropsi.commons.entity.User;
import com.ipn.escom.neuropsi.commons.entity.keys.PatientDiseaseKey;
import com.ipn.escom.neuropsi.commons.entity.values.Role;
import com.ipn.escom.neuropsi.commons.exception.UserNameNotAvailableException;
import com.ipn.escom.neuropsi.record.server.repository.DiseaseRepository;
import com.ipn.escom.neuropsi.record.server.repository.PatientDiseaseRepository;
import com.ipn.escom.neuropsi.record.server.repository.PatientRepository;
import com.ipn.escom.neuropsi.record.server.repository.UserRepository;
import com.ipn.escom.neuropsi.record.server.service.SpecialistService;
import com.ipn.escom.neuropsi.record.server.service.support.MailSupport;
import com.ipn.escom.neuropsi.record.server.service.support.UserRegistrySupport;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {

    private final UserRepository userRepository;
    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;
    private final UserRegistrySupport userRegistrySupport;
    private final PatientDiseaseRepository patientDiseaseRepository;
    private final MailSupport mailSupport;

    @Override
    public Patient savePatient(PatientRegistryDto registryDto) throws UserNameNotAvailableException, TemplateException, IOException, MessagingException {
        Patient patient = registryDto.getPatient();
        @NotNull User user = patient.getUser();
        if (userRepository.findByUsername(user.getUsername()).orElse(null) != null) {
            throw new UserNameNotAvailableException("Nombre de usuario no disponible");
        }
        List<Disease> diseases = registryDto.getDiseases();
        diseases.forEach(disease -> {
            disease = diseaseRepository.findById(disease.getIdDisease())
                    .orElseThrow(IllegalArgumentException::new);
        });
        user = userRegistrySupport.processNewUser(user, Role.PATIENT);
        user = userRepository.save(user);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("username", user.getUsername());
        parameters.put("hashcode", user.getPassword());
        mailSupport.sendMail(user.getUsername(), MailSupport.NEW_USER,
                MailSupport.NEW_USER_TEMPLATE, parameters);
        patient.setUser(user);
        patient = patientRepository.save(patient);
        Patient finalPatient = patient;
        diseases.forEach(disease -> {
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
        return patient;
    }
}
