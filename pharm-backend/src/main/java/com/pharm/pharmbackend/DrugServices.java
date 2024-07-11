package com.pharm.pharmbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DrugServices {
    private final DrugsRepository drugsRepository;

    @Autowired
    public DrugServices(DrugsRepository drugsRepository) {
        this.drugsRepository = drugsRepository;
    }

    public List<Drugs> getAllDrugs() {

        // Fetch all drugs from the repository
        List<Drugs> drugsList = drugsRepository.findAll();

        // Create an ArrayList to store the drugs
        ArrayList<Drugs> drugsArrayList = new ArrayList<>(drugsList);

        // Sort the drugsArrayList using bubble sort
        bubbleSort(drugsArrayList);

        // Return the sorted ArrayList
        return drugsArrayList;
    }

    private void bubbleSort(ArrayList<Drugs> drugsList) {
        int n = drugsList.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (drugsList.get(j).getId() > drugsList.get(j + 1).getId()) {
                    // Swap drugsList[j] and drugsList[j + 1]
                    Drugs temp = drugsList.get(j);
                    drugsList.set(j, drugsList.get(j + 1));
                    drugsList.set(j + 1, temp);
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) break;
        }
    }

    public List<Drugs> getDrugByName(String drugname) {
        List<Drugs> drugsList = drugsRepository.findAll();
        ArrayList<Drugs> drugsArrayList = new ArrayList<>(drugsList);
        return linearSearch(drugsArrayList, drugname);
    }

    private List<Drugs> linearSearch(ArrayList<Drugs> list, String drugName) {
        ArrayList<Drugs> result = new ArrayList<>();
        for (Drugs drug : list) {
            if (drug.getDrugName().equalsIgnoreCase(drugName)) {
                result.add(drug);
            }
        }
        return result;
    }

    public Drugs addDrug(Drugs drugs) {
        return drugsRepository.save(drugs);
    }

    public Optional<Drugs> updateDrug(Integer id, Drugs updatedDrugs) {
        return drugsRepository.findById(id).map(drug -> {
            drug.setDrugName(updatedDrugs.getDrugName());
            drug.setDescription(updatedDrugs.getDescription());
            drug.setQuantity(updatedDrugs.getQuantity());
            drug.setCategory(updatedDrugs.getCategory());
            drug.setBuyerId(updatedDrugs.getBuyerId());
            drug.setDate(updatedDrugs.getDate());
            drug.setTime(updatedDrugs.getTime());
            return drugsRepository.save(drug);
        });
    }

    public void deleteDrug(Integer id) {
        if (drugsRepository.existsById(id)) {
            drugsRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Drug with ID " + id + " not found.");
        }
    }
}
