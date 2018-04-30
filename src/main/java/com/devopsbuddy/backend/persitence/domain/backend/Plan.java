package com.devopsbuddy.backend.persitence.domain.backend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

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


}
