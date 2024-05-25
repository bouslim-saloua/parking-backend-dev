/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.emsi.parking.repository;

import com.emsi.parking.model.ERole;
import com.emsi.parking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bssal
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(ERole name);

}
