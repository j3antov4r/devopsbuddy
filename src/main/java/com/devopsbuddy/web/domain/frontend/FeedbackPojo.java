package com.devopsbuddy.web.domain.frontend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Arrays;

@ToString
public class FeedbackPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String feedback;


}
