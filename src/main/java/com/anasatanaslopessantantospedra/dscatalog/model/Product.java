package com.anasatanaslopessantantospedra.dscatalog.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //@Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private String imgUrl;
    @ManyToMany
    @JoinTable(name = "tb_product_category",joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "caterory_id"))
    private Set<Category> categories=new HashSet<>();
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;
   // @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
   // private Instant updateAt;

    public Product(){

    }

    public Product(Long id, String name, String description, Double price,
                   String imgUrl,Instant date, Instant updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this. date= date;
      //  this.updateAt = updateAt;
    }
/*
    @PrePersist
    public void prePersist(){
        registrationAt=Instant.now();
    }

    @PreUpdate
    public void preApdate(){
        updateAt =Instant.now();
    }

 */

    public Instant getDate() {
        return date;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public void setDate(Instant date) {
        this.date=date;
    }
}
