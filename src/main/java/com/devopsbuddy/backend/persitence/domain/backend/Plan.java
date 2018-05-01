package com.devopsbuddy.backend.persitence.domain.backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Plan implements Serializable {

    /** The Serial Version UID for Serializable classes. **/
    private static final long serialVersionUID= 1L;

    @Id
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String name;

    /*
    @OneToMany(mappedBy = "plan")
    @Getter @Setter
    private List<User> users= new ArrayList<>();
    */

}
