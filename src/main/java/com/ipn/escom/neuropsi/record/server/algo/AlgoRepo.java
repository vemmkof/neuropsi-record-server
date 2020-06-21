package com.ipn.escom.neuropsi.record.server.algo;

import com.ipn.escom.neuropsi.commons.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgoRepo extends JpaRepository<User, Long> {
}
