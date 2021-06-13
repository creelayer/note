package com.ohmynone.rest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class TagProperty extends BaseEntity {

    public enum TYPE{
        COLOR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    @Basic
    private TYPE type;

    @Basic
    private String property;
}
