package com.ipn.escom.neuropsi.record.server.service;

import com.ipn.escom.neuropsi.commons.dto.specialist.PatientRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Patient;
import com.ipn.escom.neuropsi.commons.exception.UserNameNotAvailableException;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;

public interface SpecialistService {
    Patient savePatient(PatientRegistryDto registryDto, Principal principal) throws UserNameNotAvailableException, TemplateException, IOException, MessagingException;
}
