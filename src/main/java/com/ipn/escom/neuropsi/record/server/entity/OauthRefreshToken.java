package com.ipn.escom.neuropsi.record.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshToken implements Serializable {

    private static final long serialVersionUID = 8766191624805217983L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "token_id")
    private String tokenId;

    @Lob
    @JsonIgnore
    @Column(nullable = false, length = 254 * 4, name = "token")
    private byte[] token;

    @Lob
    @JsonIgnore
    @Column(nullable = false, length = 254 * 4, name = "authentication")
    private byte[] authentication;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated")
    private Timestamp updated;


}
