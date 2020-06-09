package com.ipn.escom.neuropsi.record.server.repository;

import com.ipn.escom.neuropsi.record.server.entity.OauthAccessToken;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OauthAccessTokenRepository extends CrudRepository<OauthAccessToken, String> {

    List<OauthAccessToken> findAll(Example<OauthAccessToken> example);

}
