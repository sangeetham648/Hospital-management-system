package com.guvi.hospitalmanagement.Hospital_management_system.controller;

import com.guvi.hospitalmanagement.Hospital_management_system.entity.Appointment;
import com.guvi.hospitalmanagement.Hospital_management_system.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hms/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @PostMapping("/addAppointment")
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return appointmentRepo.save(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppoinment(){
        return appointmentRepo.findAll();
    }

    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<Map<String, Boolean>>deleteAppointment(@PathVariable long id) throws AttributeNotFoundException {
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(()-> new AttributeNotFoundException("Appointment not found with id "+id));
        appointmentRepo.delete(appointment);
        Map<String, Boolean> response=new HashMap<String, Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateAppointment/{id}")
    public ResponseEntity<Appointment> updateAppointmentById(@PathVariable long id, @RequestBody Appointment appointmentDetails) throws AttributeNotFoundException {
        Appointment appointment=appointmentRepo.findById(id).orElseThrow(()->new AttributeNotFoundException("Appointment not found with id :"+id));

        appointment.setAge(appointmentDetails.getAge());
        appointment.setName(appointmentDetails.getName());
        appointment.setSymptoms(appointmentDetails.getSymptoms());
        appointment.setNumber(appointmentDetails.getNumber());
//        appointment.setId(appointmentDetails.getId());

        Appointment savedPatient=appointmentRepo.save(appointment);

        return ResponseEntity.ok(savedPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable long id)throws AttributeNotFoundException{
        Appointment appointment = appointmentRepo.findById(id).orElseThrow(()->new AttributeNotFoundException("Appointment not found with id : "+id));
        return ResponseEntity.ok(appointment);
    }
}
