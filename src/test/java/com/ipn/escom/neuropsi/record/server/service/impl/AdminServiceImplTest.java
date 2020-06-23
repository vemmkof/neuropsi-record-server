package com.ipn.escom.neuropsi.record.server.service.impl;

import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Institute;
import com.ipn.escom.neuropsi.commons.entity.Specialist;
import com.ipn.escom.neuropsi.commons.entity.User;
import com.ipn.escom.neuropsi.commons.entity.values.Gender;
import com.ipn.escom.neuropsi.commons.entity.values.Role;
import com.ipn.escom.neuropsi.record.server.repository.InstituteRepository;
import com.ipn.escom.neuropsi.record.server.service.AdminService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private InstituteRepository instituteRepository;
    private User user;
    private Specialist specialist;
    private static final String PASSWORD = "abc123??";

    @BeforeEach
    void setUp() {
        user = User.builder()
                .name("NEUROPSI")
                .lastName("PRUEBA")
                .secondLastName("ESPECIALISTA")
                .dateOfBirth(new Date(System.currentTimeMillis()))
                .gender(Gender.MALE)
                .username("neuropsi.prueba@gmail.com")
                .password(PASSWORD)
                .phone("5566778899")
                .role(Role.ADMINISTRATOR)
                .enabled(true)
                .build();
        Institute institute = instituteRepository.findById(1L).orElse(null);
        specialist = Specialist.builder()
                .professionalId("123465789")
                .institute(institute)
                .user(user)
                .build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void saveSpecialist() throws Exception {
        SpecialistRegistryDto dto = SpecialistRegistryDto.builder()
                .specialist(specialist).build();
        specialist = adminService.saveSpecialist(dto);
        assertThat(specialist.getIdSpecialist()).isNotNull();
        assertThat(specialist.getIdSpecialist()).isGreaterThan(0);
        user = specialist.getUser();
        assertThat(user.getIdUser()).isNotNull();
        assertThat(user.getIdUser()).isGreaterThan(0);
        assertThat(user.getRole()).isEqualByComparingTo(Role.SPECIALIST);
        assertThat(user.getPassword()).isNotEqualTo(PASSWORD);
        assertThat(user.isEnabled()).isFalse();
    }
}