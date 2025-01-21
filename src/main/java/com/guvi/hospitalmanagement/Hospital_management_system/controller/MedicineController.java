package com.guvi.hospitalmanagement.Hospital_management_system.controller;

import com.guvi.hospitalmanagement.Hospital_management_system.entity.Medicine;
import com.guvi.hospitalmanagement.Hospital_management_system.entity.Patient;
import com.guvi.hospitalmanagement.Hospital_management_system.repository.MedicineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hms/medicine")
public class MedicineController {

    @Autowired
    private MedicineRepo medicineRepo;

    @PostMapping("/addMedicine")
    public Medicine addMedicine(@RequestBody Medicine medicine){
        return medicineRepo.save(medicine);
    }

    @GetMapping
    public List<Medicine> getAllMedicine(){
        return medicineRepo.findAll();
    }

    @DeleteMapping("/deleteMedicine/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMedicine(@PathVariable long id) throws AttributeNotFoundException {
        Medicine medicine = medicineRepo.findById(id).orElseThrow(()-> new AttributeNotFoundException("Medicine not found with id "+id));
        medicineRepo.delete(medicine);
        Map<String, Boolean> response=new HashMap<String, Boolean>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
