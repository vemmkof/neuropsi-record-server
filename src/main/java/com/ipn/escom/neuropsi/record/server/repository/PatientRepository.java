package com.ipn.escom.neuropsi.record.server.repository;

import com.ipn.escom.neuropsi.record.server.entity.Patient;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient> findAll(Example<Patient> example);

}
