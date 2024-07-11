package com.pharm.pharmbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("suppliers")
@CrossOrigin(origins = "http://localhost:5173")
public class SupplyController {
    private final SupplyServices supplyServices;

    @Autowired
    public SupplyController(SupplyServices supplyServices) {
        this.supplyServices = supplyServices;
    }

    @GetMapping
    public List<Supply> getAllSupplies() {
        return supplyServices.getAllSupplies();
    }

    @GetMapping("/{country}")
    public List<Supply> getSupplyByCountry(@PathVariable String country){
        return supplyServices.getSupplierByCountry(country);
    }

    @PostMapping
    public Supply addSupply(@RequestBody Supply supply) {
        return supplyServices.addSupply(supply);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supply> updateSupply(@PathVariable Integer id, @RequestBody Supply updatedSupply) {
        return supplyServices.updateSupply(id, updatedSupply)
                .map(updated -> ResponseEntity.ok().body(updated))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupply(@PathVariable Integer id) {
        try {
            supplyServices.deleteSupply(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
