package com.mystore.codeshop.entity;

import jakarta.persistence.*;
/*
 * represent the database entity for roles
 * works with ERole to dynamically assign roles to user
 */
@Entity
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  public Role() {

  }

  public Role(ERole name) {
    this.name = name;
  }

  public void setRole(ERole name) {
    this.name = name;
  }
  public ERole getName() {
    return name;
  }

}
