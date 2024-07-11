package com.pharm.pharmbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purchase")
@CrossOrigin(origins = "http://localhost:5173" )
public class PurchaseController {
    private final PurchaseServices purchaseServices;

    @Autowired
    public PurchaseController(PurchaseServices purchaseServices){
        this.purchaseServices = purchaseServices;
    }

    @GetMapping
    public List<Purchase> getAllPurchases(){
        return purchaseServices.getAllPurchases();
    }

    @GetMapping("/{drugname}")
    public List<Purchase> getDrugByPurchasedDrug(@PathVariable String drugname){
        return purchaseServices.getPurchasedDrugByName(drugname);
    }


    @PostMapping
    public Purchase addPurchase(@RequestBody Purchase purchase){
        return purchaseServices.addPurchase(purchase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Integer id, @RequestBody Purchase updatedPurchase){
        return purchaseServices.updatePurchase(id, updatedPurchase)
                .map(updated -> ResponseEntity.ok().body(updated))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Integer id){
        try {
            purchaseServices.deletePurchase(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
