package com.devopsbuddy.backend.persitence.domain.backend;

import com.devopsbuddy.utils.MD5Util;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User implements Serializable, UserDetails{

    /** The Serial Version UID for Serializable classes. **/
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String username;

     @Setter
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Override
    public String getPassword() {
        return MD5Util.md5Hex(password);
    }

    @Getter @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities= new HashSet<>();
        userRoles.forEach(ur-> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
