package com.ipn.escom.neuropsi.record.server.repository;

import com.ipn.escom.neuropsi.record.server.entity.Question;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findAll(Example<Question> example);

}
