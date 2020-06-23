package com.ipn.escom.neuropsi.record.server.service;

import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Specialist;

public interface AdminService {
    Specialist saveSpecialist(SpecialistRegistryDto registryDto);
}
