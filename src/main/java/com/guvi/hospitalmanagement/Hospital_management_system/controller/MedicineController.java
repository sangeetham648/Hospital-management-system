package com.guvi.hospitalmanagement.Hospital_management_system.controller;

import com.guvi.hospitalmanagement.Hospital_management_system.entity.Medicine;
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

    @PutMapping("/updateMedicine/{id}")
    public ResponseEntity<Medicine> updateMedicineById(@PathVariable long id,@RequestBody Medicine medicineDetails) throws AttributeNotFoundException {
        Medicine medicine=medicineRepo.findById(id).orElseThrow(()->new AttributeNotFoundException("Medicine not found with id :"+id));

//        medicine.setId(medicineDetails.getId());
        medicine.setDrugName(medicineDetails.getDrugName());
        medicine.setStock(medicineDetails.getStock());

        Medicine savedMedicine=medicineRepo.save(medicine);

        return ResponseEntity.ok(savedMedicine);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getPatientById(@PathVariable long id)throws AttributeNotFoundException{
        Medicine medicine = medicineRepo.findById(id).orElseThrow(()->new AttributeNotFoundException("Medicine not found with id : "+id));
        return ResponseEntity.ok(medicine);
    }

}
