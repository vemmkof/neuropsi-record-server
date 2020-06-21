package com.ipn.escom.neuropsi.record.server.entity.keys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PatientKey implements Serializable {
    private static final long serialVersionUID = 2999312324510580136L;
    @Column(name = "id_user")
    private Long idUser;
}
