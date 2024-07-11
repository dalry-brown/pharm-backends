package com.pharm.pharmbackend;
import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supply {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "supplier_id")
    @Column(name = "supplier_id", updatable = false)
    @SequenceGenerator(
            name = "supply_sequence",
            sequenceName = "supply-sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "supply_sequence"
    )
    private Integer supplierId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "country", nullable = false)
    private String country;

    @ManyToOne()
    @JoinColumn(name = "drug_id", referencedColumnName = "id", nullable = false)
    private Drugs drug;

    // Constructors, getters, and setters

    public Supply() {
    }

    public Supply(String name, String phone, String email, String streetAddress, String country, Drugs drug) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.streetAddress = streetAddress;
        this.country = country;
        this.drug = drug;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Drugs getDrug() {
        return drug;
    }

    public void setDrug(Drugs drug) {
        this.drug = drug;
    }
}
