package com.example.bdbj.dto;

import com.example.bdbj.domain.Category;

public class MenuReqDto {
    private final String menuName;
    private final Category category;
    private final Integer price;
    private final String imagePath;
    private final String description;

    public MenuReqDto(String menuName, Category category, Integer price, String imagePath, String description) {
        this.menuName = menuName;
        this.category = category;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
    }

    public String getMenuName() {
        return menuName;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }
}
