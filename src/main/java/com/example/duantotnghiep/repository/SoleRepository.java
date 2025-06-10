package com.example.duantotnghiep.repository;


import com.example.duantotnghiep.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT p FROM Role p where p.status = 1 order by p.createdDate desc ")
    List<Role> findByStatus();
}
