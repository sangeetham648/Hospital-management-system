package com.guvi.hospitalmanagement.Hospital_management_system.repository;

import com.guvi.hospitalmanagement.Hospital_management_system.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}