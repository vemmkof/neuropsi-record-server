package com.ipn.escom.neuropsi.record.server.entity;

import com.ipn.escom.neuropsi.record.server.entity.keys.PatientKey;
import com.ipn.escom.neuropsi.record.server.entity.values.Laterality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient implements Serializable {

    private static final long serialVersionUID = -1744235761499622948L;

    @EmbeddedId
    private PatientKey patientKey;

    @NotNull
    @OneToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    private User user;

    @NotEmpty
    @Column(nullable = false)
    private String reasonForConsultation;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Laterality laterality;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated")
    private Timestamp updated;
}
