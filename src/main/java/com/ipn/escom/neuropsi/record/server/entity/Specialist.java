package com.ipn.escom.neuropsi.record.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Specialist implements Serializable {

    private static final long serialVersionUID = -1027900055620607942L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
    private Long idSpecialist;

    @NotEmpty
    @Column(nullable = false)
    private String professionalId;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_insitute", nullable = false)
    private Institute institute;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "specialist_patient",
            joinColumns = {@JoinColumn(name = "id_specialist", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_patient", nullable = false)})
    private List<Patient> patients;

}
