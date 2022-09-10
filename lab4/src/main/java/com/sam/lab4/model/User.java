package com.sam.lab4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ssoId", unique = true, nullable = false)
    private String ssoId;
    private String fullname;
    private String password;
    private String email;
    @ManyToMany
    @JoinTable(name = "userrolemapping", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name="ROLE_ID")})
    private Set<UserRole> userRoleSet;
}
