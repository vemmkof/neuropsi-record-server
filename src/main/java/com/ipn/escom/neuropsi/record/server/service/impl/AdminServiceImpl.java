package com.ipn.escom.neuropsi.record.server.service.impl;

import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Institute;
import com.ipn.escom.neuropsi.commons.entity.Specialist;
import com.ipn.escom.neuropsi.commons.entity.User;
import com.ipn.escom.neuropsi.commons.entity.values.Role;
import com.ipn.escom.neuropsi.record.server.repository.InstituteRepository;
import com.ipn.escom.neuropsi.record.server.repository.SpecialistRepository;
import com.ipn.escom.neuropsi.record.server.repository.UserRepository;
import com.ipn.escom.neuropsi.record.server.service.AdminService;
import com.ipn.escom.neuropsi.record.server.service.support.UserRegistrySupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final InstituteRepository instituteRepository;
    private final SpecialistRepository specialistRepository;
    private final UserRegistrySupport userRegistrySupport;

    @Override
    @Transactional
    public Specialist saveSpecialist(SpecialistRegistryDto registryDto) {
        Specialist specialist = registryDto.getSpecialist();
        @NotNull User user = specialist.getUser();
        @NotNull Institute institute = specialist.getInstitute();
        user = userRegistrySupport.processNewUser(user, Role.SPECIALIST);
        user = userRepository.save(user);
        institute = instituteRepository
                .findById(institute.getIdInstitute())
                .orElse(null);
        specialist.setUser(user);
        specialist.setInstitute(institute);
        return specialistRepository.save(specialist);
    }
}
