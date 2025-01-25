package com.guvi.hospitalmanagement.Hospital_management_system.controller;

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

    @PutMapping("/updatePatient/{id}")
    public ResponseEntity<Patient> updatePatientById(@PathVariable long id,@RequestBody Patient patientDetails) throws AttributeNotFoundException {
        Patient patient=patientRepo.findById(id).orElseThrow(()->new AttributeNotFoundException("Patient not found with id : "+id));

        patient.setAge(patientDetails.getAge());
        patient.setName(patientDetails.getName());
        patient.setDose(patientDetails.getDose());
        patient.setFees(patientDetails.getFees());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        patient.setPrescription(patientDetails.getPrescription());
        patient.setUrgency(patientDetails.getUrgency());
//        patient.setId(patientDetails.getId());

        Patient savedPatient=patientRepo.save(patient);

        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable long id)throws AttributeNotFoundException{
        Patient patient = patientRepo.findById(id).orElseThrow(()->new AttributeNotFoundException("Patient not found with id : "+id));
        return ResponseEntity.ok(patient);
    }
}
