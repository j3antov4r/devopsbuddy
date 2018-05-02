package com.devopsbuddy.backend.persitence.domain.backend;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class UserRole implements Serializable {

    /** The Serial Version UID for Serializable classes. **/
    private static final long serialVersionUID= 1L;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @Getter @Setter
    private Role role;
}
