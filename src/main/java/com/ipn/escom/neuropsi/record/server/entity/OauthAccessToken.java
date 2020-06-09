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
@Table(name = "oauth_access_token")
public class OauthAccessToken implements Serializable {

    private static final long serialVersionUID = -715587613930346354L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authentication_id")
    private String authenticationId;

    @Column(nullable = false, name = "token_id")
    private String tokenId;

    @Lob
    @JsonIgnore
    @Column(nullable = false, length = 254 * 4, name = "token")
    private byte[] token;

    @Column(nullable = false, name = "user_name")
    private String userName;

    @Column(nullable = false, name = "client_id")
    private String clientId;

    @Lob
    @JsonIgnore
    @Column(nullable = false, length = 254 * 4, name = "authentication")
    private byte[] authentication;

    @Column(nullable = false, name = "refresh_token")
    private String refreshToken;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "created")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated")
    private Timestamp updated;

}
