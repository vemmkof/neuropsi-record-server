package com.ipn.escom.neuropsi.record.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Test implements Serializable {

    private static final long serialVersionUID = -3392053809620917954L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
    private Long idTest;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_specialist", nullable = false)
    private Specialist specialist;


    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean finished;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updated;
}
