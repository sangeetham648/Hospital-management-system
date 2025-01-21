package com.guvi.hospitalmanagement.Hospital_management_system.controller;

import com.guvi.hospitalmanagement.Hospital_management_system.entity.Appointment;
import com.guvi.hospitalmanagement.Hospital_management_system.entity.Patient;
import com.guvi.hospitalmanagement.Hospital_management_system.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hms/patient")
public class PatientController {

    @Autowired
    private PatientRepo patientRepo;

    @PostMapping("/addPatient")
    public Patient addPatient(@RequestBody Patient patient){
        return patientRepo.save(patient);
    }

    @GetMapping
    public List<Patient> getAllPatient(){
        return patientRepo.findAll();
    }

    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable long id) throws AttributeNotFoundException {
        Patient patient = patientRepo.findById(id).orElseThrow(()-> new AttributeNotFoundException("Appointment not found with id "+id));
        patientRepo.delete(patient);
        Map<String, Boolean> response=new HashMap<String, Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
