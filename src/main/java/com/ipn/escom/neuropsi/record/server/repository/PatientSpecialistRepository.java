package com.ipn.escom.neuropsi.record.server.repository;

import com.ipn.escom.neuropsi.commons.entity.PatientSpecialist;
import com.ipn.escom.neuropsi.commons.entity.keys.PatientSpecialistKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientSpecialistRepository extends JpaRepository<PatientSpecialist, PatientSpecialistKey> {
}
