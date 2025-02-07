package com.mystore.codeshop.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mystore.codeshop.entity.ERole;
import com.mystore.codeshop.entity.Role;
//This will be AUTO IMPLEMENTED by Spring into a Bean called roleRepository
// auto manages our role repo
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
