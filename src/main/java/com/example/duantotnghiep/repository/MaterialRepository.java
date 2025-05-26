package com.example.duantotnghiep.repository;

import com.example.duantotnghiep.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Long> {
}
