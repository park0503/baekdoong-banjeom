package com.example.bdbj.dto;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;

import java.util.UUID;

public class SimpleMenuResDto {
    private final UUID menuId;
    private final String menuName;
    private final Category category;
    private final Integer price;
    private final String imagePath;

    public SimpleMenuResDto(Menu entity) {
        this.menuId = entity.getMenuId();
        this.menuName = entity.getMenuName();
        this.category = entity.getCategory();
        this.price = entity.getPrice();
        this.imagePath = entity.getImagePath();
    }

    public UUID getMenuId() {
        return menuId;
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
}
