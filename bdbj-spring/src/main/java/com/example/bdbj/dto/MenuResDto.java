package com.example.bdbj.dto;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MenuResDto {
    private final UUID menuId;
    private final String menuName;
    private final Category category;
    private final Integer price;
    private final String imagePath;
    private final String description;

    public MenuResDto(Menu entity) {
        this.menuId = entity.getMenuId();
        this.menuName = entity.getMenuName();
        this.category = entity.getCategory();
        this.price = entity.getPrice();
        this.imagePath = entity.getImagePath();
        this.description = entity.getDescription();
    }
}
