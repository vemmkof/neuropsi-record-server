package com.ipn.escom.neuropsi.record.server.repository;

import com.ipn.escom.neuropsi.commons.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
}
