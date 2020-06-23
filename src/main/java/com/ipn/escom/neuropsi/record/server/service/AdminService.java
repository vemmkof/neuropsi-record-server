package com.ipn.escom.neuropsi.record.server.service;

import com.ipn.escom.neuropsi.commons.dto.admin.SpecialistRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Specialist;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface AdminService {
    Specialist saveSpecialist(SpecialistRegistryDto registryDto) throws TemplateException, IOException, MessagingException;
}
