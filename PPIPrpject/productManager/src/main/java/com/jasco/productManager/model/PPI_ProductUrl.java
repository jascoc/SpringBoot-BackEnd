package com.jasco.productManager.model;

public class PPI_ProductUrl {
    private String name;
    private String description;
    private int storageCount;
    private String image;

    public PPI_ProductUrl(String name, String description, int storageCount, String image) {
        this.name = name;
        this.description = description;
        this.storageCount = storageCount;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }
}
