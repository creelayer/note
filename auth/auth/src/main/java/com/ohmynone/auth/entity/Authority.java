package com.ohmynone.auth.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Authority {

    @Id
    @GeneratedValue
    private UUID uid;

    @Basic
    private String name;
}
