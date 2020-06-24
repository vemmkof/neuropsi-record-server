package com.ipn.escom.neuropsi.record.server.repository;

import com.ipn.escom.neuropsi.commons.entity.PatientDisease;
import com.ipn.escom.neuropsi.commons.entity.keys.PatientDiseaseKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDiseaseRepository extends JpaRepository<PatientDisease, PatientDiseaseKey> {
}
