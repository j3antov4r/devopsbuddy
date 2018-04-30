package com.devopsbuddy.backend.persitence.domain.backend;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable{

    /** The Serial Version UID for Serializable classes. **/
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;

    @Column(name = "first_name")
    @Getter @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter @Setter
    private String lastName;

    @Column(name = "phone_number")
    @Getter @Setter
    private String phoneNumber;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String country;

    @Getter @Setter
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Getter @Setter
    @Column(name = "stripe_customer_id")
    private String stripeCustomerId;

    @Getter @Setter
    private Boolean enabled;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Getter @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

}
