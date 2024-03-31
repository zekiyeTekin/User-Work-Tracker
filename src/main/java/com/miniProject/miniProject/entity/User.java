package com.miniProject.miniProject.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String surname;

    //@JsonBackReference
    //@JsonIgnore
    @OneToMany(mappedBy = "user")
    //@JoinColumn(name ="user_id") //unutmuşum
    private List<Assignment> assignmentList;
}
