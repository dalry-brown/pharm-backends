package com.pharm.pharmbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PurchaseServices {
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServices(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        ArrayList<Purchase> purchaseArrayList = new ArrayList<>(purchaseList);
        quickSort(purchaseArrayList, 0, purchaseArrayList.size() - 1);
        return purchaseArrayList;
    }

    private void quickSort(ArrayList<Purchase> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);  // Before pi
            quickSort(list, pi + 1, high); // After pi
        }
    }

    private int partition(ArrayList<Purchase> list, int low, int high) {
        int pivot = list.get(high).getPurchaseId();
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (list.get(j).getPurchaseId() < pivot) {
                i++;
                // Swap list[i] and list[j]
                Purchase temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Swap list[i + 1] and list[high] (or pivot)
        Purchase temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    public  Purchase addPurchase(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public Optional<Purchase> updatePurchase(Integer id, Purchase updatedPurchase){
        return purchaseRepository.findById(id).map(purchase -> {
            purchase.setPurchaseId(updatedPurchase.getBuyerId());
            purchase.setDrug(updatedPurchase.getDrug());
            purchase.setQuantity(updatedPurchase.getQuantity());
            purchase.setDate(updatedPurchase.getDate());
            purchase.setTime(updatedPurchase.getTime());
            return purchaseRepository.save(purchase);
        });
    }

    public void deletePurchase(Integer id){
        if(purchaseRepository.existsById(id)){
            purchaseRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Purchase with ID " + id + " not found.");
        }
    }

    public List<Purchase> getPurchasedDrugByName(String drugname) {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        ArrayList<Purchase> purchaseArrayList = new ArrayList<>(purchaseList);
        // Sort the list by drug name
        purchaseArrayList.sort(Comparator.comparing(p -> p.getDrug().getDrugName()));

        // Perform binary search
        return binarySearch(purchaseArrayList, drugname);
    }

    private List<Purchase> binarySearch(ArrayList<Purchase> list, String drugName) {
        int left = 0;
        int right = list.size() - 1;
        List<Purchase> result = new ArrayList<>();

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midDrugName = list.get(mid).getDrug().getDrugName();

            if (midDrugName.equalsIgnoreCase(drugName)) {
                // Find all matching purchases
                result.add(list.get(mid));
                // Check to the left of mid
                int i = mid - 1;
                while (i >= 0 && list.get(i).getDrug().getDrugName().equalsIgnoreCase(drugName)) {
                    result.add(list.get(i));
                    i--;
                }
                // Check to the right of mid
                int j = mid + 1;
                while (j < list.size() && list.get(j).getDrug().getDrugName().equalsIgnoreCase(drugName)) {
                    result.add(list.get(j));
                    j++;
                }
                break;
            } else if (midDrugName.compareToIgnoreCase(drugName) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}
