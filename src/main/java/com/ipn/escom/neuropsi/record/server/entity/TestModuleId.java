package com.ipn.escom.neuropsi.record.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TestModuleId implements Serializable {

    private static final long serialVersionUID = 1091398173032533848L;

    private Test test;

    private Module module;

}
