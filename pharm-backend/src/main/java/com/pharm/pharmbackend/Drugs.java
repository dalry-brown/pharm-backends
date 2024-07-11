//Mikey_Final

package com.pharm.pharmbackend;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table
public class Drugs {

    // id Integer primary key
// drugName VARCHAR(10)
// description VARCHAR(100)
// quantity INTEGER
// category TEXT[]
// buyerId Integer
// date DATE
// time TIME

    @Id
    @SequenceGenerator(
            name = "id_sequence_generator",
            sequenceName = "id_sequence_generator",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_sequence_generator")

    @Column( updatable = false)
    private Integer id;

    @Column( nullable = false, length = 50) // Maps the field to a column with constraints
    private String drugName;

    @Column( length = 255)
    private String description;

    @Column( nullable = false, length = 255)
    private Integer quantity;

    @Column( nullable = false, length = 255)
    private List<String> category;

    @Column(nullable = false)
    private Integer buyerId;

    @Column( nullable = false)
    private LocalDate date;

    @Column( nullable = false)
    private LocalTime time;


    public Drugs(Integer id, String drugName, String description, Integer quantity, List<String> category, Integer buyerId, LocalDate date, LocalTime time) {
        this.id = id;
        this.drugName = drugName;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.buyerId = buyerId;
        this.date = date;
        this.time = time;
    }

    public Drugs(String drugName, String description, Integer quantity, List<String> category, LocalDate date, Integer buyerId, LocalTime time) {
        this.drugName = drugName;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
        this.buyerId = buyerId;
        this.date = date;
        this.time = time;
    }

    public Drugs() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
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
        return "Drugs{" +
                "id=" + id +
                ", drugName='" + drugName + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", category=" + category +
                ", buyerId=" + buyerId +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
