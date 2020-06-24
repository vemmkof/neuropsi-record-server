package com.ipn.escom.neuropsi.record.server.service;

import com.ipn.escom.neuropsi.commons.dto.admin.InstituteRegistryDto;
import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Institute;
import com.ipn.escom.neuropsi.commons.entity.Specialist;
import com.ipn.escom.neuropsi.commons.entity.values.Role;
import com.ipn.escom.neuropsi.commons.exception.UserNameNotAvailableException;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;

public interface AdminService {
    
    Specialist saveSpecialist(SpecialistRegistryDto registryDto, Role role) throws TemplateException, IOException, MessagingException, UserNameNotAvailableException;

    Institute saveInstitute(InstituteRegistryDto registryDto);

    ArrayList<Institute> getInstitutes();
}
