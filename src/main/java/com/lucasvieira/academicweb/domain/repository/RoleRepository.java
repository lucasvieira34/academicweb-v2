package com.lucasvieira.academicweb.domain.repository;

import com.lucasvieira.academicweb.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
