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
public class SpecialistKey implements Serializable {
    private static final long serialVersionUID = 7033449067986974009L;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "id_institute")
    private Long idInstitute;
}
