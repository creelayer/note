package com.ohmynone.rest.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class Tag extends BaseEntity {

    public Tag(){
    }

    public Tag(User user, String name){
        this.user = user;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "tag")
    private Set<TagProperty> properties;

    @Basic
    private String name;

    @Column(length = 6)
    private String color;
}
