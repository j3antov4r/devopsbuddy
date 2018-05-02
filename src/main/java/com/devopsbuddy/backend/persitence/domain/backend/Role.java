package com.devopsbuddy.backend.persitence.domain.backend;

import com.devopsbuddy.enums.RolesEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Role implements Serializable {

    /** The Serial Version UID for Serializable classes. **/
    private static final long serialVersionUID= 1L;

    @Id
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    /**
     * Full constructor
     * @param rolesEnum
     * */
    public Role(RolesEnum rolesEnum){
        this.id= rolesEnum.getId();
        this.name = rolesEnum.getRoleName();
    }
}
