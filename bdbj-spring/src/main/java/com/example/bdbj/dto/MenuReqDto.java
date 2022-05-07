package com.example.bdbj.dto;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.FieldBlankException;

public class MenuReqDto {
    private final String menuName;
    private final Category category;
    private final Integer price;
    private final String imagePath;
    private final String description;

    public MenuReqDto(String menuName, Category category, Integer price, String imagePath, String description) {
        if (menuName == null || category == null || price == null || imagePath == null || description == null) {
            throw new FieldBlankException("입력값이 부족합니다.", ErrorCode.FIELD_BLANK);
        }
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
