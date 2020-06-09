package com.ipn.escom.neuropsi.record.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestModule implements Serializable {

    private static final long serialVersionUID = -1686481585875057465L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private TestModuleId id;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean finished;

    @Column(nullable = false, columnDefinition = "DOUBLE DEFAULT 0.0")
    private Double score;

    private Timestamp startedAt;

    private Timestamp finishedAt;


}
