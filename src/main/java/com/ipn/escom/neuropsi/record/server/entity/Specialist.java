package com.ipn.escom.neuropsi.record.server.entity;

import com.ipn.escom.neuropsi.record.server.entity.keys.SpecialistKey;
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
public class Specialist implements Serializable {

    private static final long serialVersionUID = -1027900055620607942L;

    @NotNull
    @EmbeddedId
    private SpecialistKey specialistKey;

    @NotNull
    @ManyToOne
    @MapsId("id_institute")
    @JoinColumn(name = "id_institute")
    private Institute institute;

    @NotNull
    @OneToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String professionalId;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated")
    private Timestamp updated;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "specialist_patient",
//            joinColumns = {@JoinColumn(name = "id_specialist", nullable = false)},
//            inverseJoinColumns = {@JoinColumn(name = "id_patient", nullable = false)})
//    private List<Patient> patients;

}
