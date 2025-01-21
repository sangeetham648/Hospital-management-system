package com.guvi.hospitalmanagement.Hospital_management_system.repository;

import com.guvi.hospitalmanagement.Hospital_management_system.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {
}
