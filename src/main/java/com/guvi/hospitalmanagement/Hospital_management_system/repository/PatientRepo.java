package com.guvi.hospitalmanagement.Hospital_management_system.repository;

import com.guvi.hospitalmanagement.Hospital_management_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {
}