package com.ipn.escom.neuropsi.record.server.repository;

import com.ipn.escom.neuropsi.record.server.entity.Specialist;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SpecialistRepository extends CrudRepository<Specialist, Long> {

    List<Specialist> findAll(Example<Specialist> example);

}
