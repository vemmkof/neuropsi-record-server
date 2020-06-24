package com.ipn.escom.neuropsi.record.server.service;

import com.ipn.escom.neuropsi.commons.dto.specialist.PatientRegistryDto;
import com.ipn.escom.neuropsi.commons.entity.Patient;
import com.ipn.escom.neuropsi.commons.exception.UserNameNotAvailableException;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface SpecialistService {
    Patient savePatient(PatientRegistryDto registryDto) throws UserNameNotAvailableException, TemplateException, IOException, MessagingException;
}
