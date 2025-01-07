
package com.shoping.mavenproject7.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PId;
    private String pName;
    @Column(length = 4000)
    private String pDisc;
    private String pPhoto;
    private int  pPrice;
    private int pDiscount;
    private int pQuantity;
    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String pName, String pDisc, String pPhoto, int pPrice, int pDiscount, int pQuantity,Category category) {
        this.pName = pName;
        this.pDisc = pDisc;
        this.pPhoto = pPhoto;
        this.pPrice = pPrice;
        this.pDiscount = pDiscount;
        this.pQuantity = pQuantity;
        this.category = category;
    }

    public int getPId() {
        return PId;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDisc() {
        return pDisc;
    }

    public void setpDisc(String pDisc) {
        this.pDisc = pDisc;
    }

    public String getpPhoto() {
        return pPhoto;
    }

    public void setpPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public int getpDiscount() {
        return pDiscount;
    }

    public void setpDiscount(int pDiscount) {
        this.pDiscount = pDiscount;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    

    @Override
    public String toString() {
        return "Product{" + "PId=" + PId + ", pName=" + pName + ", pDisc=" + pDisc + ", pPhoto=" + pPhoto + ", pPrice=" + pPrice + ", pDiscount=" + pDiscount + ", pQuantity=" + pQuantity + '}';
    }
    public int getPriceAfterDiscount(){
        int d= (int)((this.getpDiscount()/100.0)*this.getpPrice());
        return this.getpPrice()-d;
    }
    
    
    
}
