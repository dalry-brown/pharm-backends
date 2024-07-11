package com.pharm.pharmbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplyServices {
    private final SupplyRepository supplyRepository;

    @Autowired
    public SupplyServices(SupplyRepository supplyRepository) {
        this.supplyRepository = supplyRepository;
    }

    public List<Supply> getAllSupplies() {
        List<Supply> supplyList = supplyRepository.findAll();
        ArrayList<Supply> supplyArrayList = new ArrayList<>(supplyList);
        selectionSort(supplyArrayList);
        return supplyArrayList;
    }

    private void selectionSort(ArrayList<Supply> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in the unsorted part of the list
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getSupplierId() < list.get(minIndex).getSupplierId()) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            Supply temp = list.get(minIndex);
            list.set(minIndex, list.get(i));
            list.set(i, temp);
        }
    }



    public Supply addSupply(Supply supply) {
        return supplyRepository.save(supply);
    }

    public Optional<Supply> updateSupply(Integer id, Supply updatedSupply) {
        return supplyRepository.findById(id).map(supply -> {
            supply.setSupplierId(updatedSupply.getSupplierId());
            supply.setName(updatedSupply.getName());
            supply.setPhone(updatedSupply.getPhone());
            supply.setEmail(updatedSupply.getEmail());
            supply.setStreetAddress(updatedSupply.getStreetAddress());
            supply.setCountry(updatedSupply.getCountry());
            supply.setDrug(updatedSupply.getDrug());
            return supplyRepository.save(supply);
        });
    }

    public void deleteSupply(Integer id) {
        if (supplyRepository.existsById(id)) {
            supplyRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Supply with ID " + id + " not found.");
        }
    }

    public List<Supply> getSupplierByCountry(String country) {
        List<Supply> supplyList = supplyRepository.findAll();
        ArrayList<Supply> supplyArrayList = new ArrayList<>(supplyList);
        return linearSearch(supplyArrayList, country);
    }

    private List<Supply> linearSearch(ArrayList<Supply> list, String country) {
        ArrayList<Supply> result = new ArrayList<>();
        for (Supply supply : list) {
            if (supply.getCountry().equalsIgnoreCase(country)) {
                result.add(supply);
            }
        }
        return result;
    }
}
