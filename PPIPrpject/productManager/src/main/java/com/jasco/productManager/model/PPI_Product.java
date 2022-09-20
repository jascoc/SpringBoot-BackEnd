package com.jasco.productManager.model;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table
public class PPI_Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String description;
    private byte[] image;
    private int storageCount;

    public PPI_Product() {}

    public PPI_Product(String name, String description, byte[] image, int storageCount) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.storageCount = storageCount;
    }

    //only used for test purpose
    public PPI_Product(String name, String description, int storageCount) {
        this.name = name;
        this.description = description;
        this.storageCount = storageCount;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }

    @Override
    public String toString() {
        return "PPI_Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                ", storageCount=" + storageCount +
                '}';
    }
}
