package com.pharm.pharmbackend;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table
public class Purchase {

    @Id
    @Column(name = "purchase_id", updatable = false)
    @SequenceGenerator(
            name = "purchase_sequence",
            sequenceName = "purchase-sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "purchase_sequence"
    )
    private Integer purchaseId;

    @Column(name = "buyer_id", updatable = false)
    private Integer buyerId;

    @ManyToOne
    @JoinColumn(name = "drug_id", referencedColumnName = "id")
    private Drugs drug;

    @Column(name = "quantity", nullable = false,length = 255)
    private Integer quantity;

    @Column( nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    public Purchase(Integer purchaseId, Integer buyerId, Drugs drug, Integer quantity, LocalDate date, LocalTime time) {
        this.purchaseId = purchaseId;
        this.buyerId = buyerId;
        this.drug = drug;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
    }

    public Purchase(Integer buyerId, Drugs drug, Integer quantity, LocalDate date, LocalTime time) {
        this.buyerId = buyerId;
        this.drug = drug;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
    }

    public Purchase() {

    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Drugs getDrug() {
        return drug;
    }

    public void setDrug(Drugs drugs) {
        this.drug = drugs;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", buyerId=" + buyerId +
                ", drugs=" + drug +
                ", quantity=" + quantity +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
