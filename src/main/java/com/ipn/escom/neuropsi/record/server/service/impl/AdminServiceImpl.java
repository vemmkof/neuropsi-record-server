package com.ipn.escom.neuropsi.record.server.service.impl;

import com.ipn.escom.neuropsi.commons.dto.admin.InstituteRegistryDto;
import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Institute;
import com.ipn.escom.neuropsi.commons.entity.Specialist;
import com.ipn.escom.neuropsi.commons.entity.User;
import com.ipn.escom.neuropsi.commons.entity.values.Role;
import com.ipn.escom.neuropsi.commons.exception.UserNameNotAvailableException;
import com.ipn.escom.neuropsi.record.server.repository.InstituteRepository;
import com.ipn.escom.neuropsi.record.server.repository.SpecialistRepository;
import com.ipn.escom.neuropsi.record.server.repository.UserRepository;
import com.ipn.escom.neuropsi.record.server.service.AdminService;
import com.ipn.escom.neuropsi.record.server.service.support.MailSupport;
import com.ipn.escom.neuropsi.record.server.service.support.UserRegistrySupport;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final InstituteRepository instituteRepository;
    private final SpecialistRepository specialistRepository;
    private final UserRegistrySupport userRegistrySupport;
    private final MailSupport mailSupport;

    @Override
    @Transactional
    public Specialist saveSpecialist(SpecialistRegistryDto registryDto, Role role) throws TemplateException, IOException, MessagingException, UserNameNotAvailableException {
        Specialist specialist = registryDto.getSpecialist();
        @NotNull User user = specialist.getUser();
        if (userRepository.findByUsername(user.getUsername()).orElse(null) != null) {
            throw new UserNameNotAvailableException("Nombre de usuario no disponible");
        }
        @NotNull Institute institute = specialist.getInstitute();
        user = userRegistrySupport.processNewUser(user, role);
        user = userRepository.save(user);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", user.getName());
        parameters.put("username", user.getUsername());
        parameters.put("hashcode", user.getPassword());
        mailSupport.sendMail(user.getUsername(), MailSupport.NEW_USER,
                MailSupport.NEW_USER_TEMPLATE, parameters);
        institute = instituteRepository.findById(institute.getIdInstitute())
                .orElse(null);
        specialist.setUser(user);
        specialist.setInstitute(institute);
        return specialistRepository.save(specialist);
    }

    @Override
    public Institute saveInstitute(InstituteRegistryDto registryDto) {
        return instituteRepository.save(registryDto.getInstitute());
    }

    @Override
    public ArrayList<Institute> getInstitutes() {
        return new ArrayList<>(instituteRepository.findAll());
    }

}
