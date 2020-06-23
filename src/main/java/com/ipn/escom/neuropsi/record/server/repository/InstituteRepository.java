package com.ipn.escom.neuropsi.record.server.repository;

import com.ipn.escom.neuropsi.commons.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<Institute, Long> {
}
