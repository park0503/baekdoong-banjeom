package com.example.bdbj.domain;

import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.InvalidInputException;
import com.example.bdbj.util.GlobalUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Menu {
    private final UUID menuId;
    private String menuName;
    private Category category;
    private Integer price;
    private String imagePath;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Menu(UUID menuId, String menuName, Category category, Integer price, String imagePath, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        GlobalUtils.checkMenuNull(menuId, menuName, category, price);
        checkPrice(price);
        this.menuId = menuId;
        this.menuName = menuName;
        this.category = category;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now().withNano(0);
        this.updatedAt = updatedAt;
    }

    public Menu(UUID menuId, String menuName, Category category, Integer price, String imagePath, String description) {
        GlobalUtils.checkMenuNull(menuId, menuName, category, price);
        checkPrice(price);
        this.menuId = menuId;
        this.menuName = menuName;
        this.category = category;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.createdAt = LocalDateTime.now().withNano(0);
    }

    public Menu(UUID menuId, Integer price, String menuName, Category category) {
        GlobalUtils.checkMenuNull(menuId, menuName, category, price);
        checkPrice(price);
        this.menuId = menuId;
        this.price = price;
        this.menuName = menuName;
        this.category = category;
        this.createdAt = LocalDateTime.now().withNano(0);
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

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setMenuName(String menuName) {
        GlobalUtils.checkNull(menuName);
        this.menuName = menuName;
        updatedUpdatedAt();
    }

    public void setCategory(Category category) {
        GlobalUtils.checkNull(category);
        this.category = category;
        updatedUpdatedAt();
    }

    public void setPrice(Integer price) {
        GlobalUtils.checkNull(price);
        checkPrice(price);
        this.price = price;
        updatedUpdatedAt();
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        updatedUpdatedAt();
    }

    public void setDescription(String description) {
        this.description = description;
        updatedUpdatedAt();
    }

    private void updatedUpdatedAt() {
        this.updatedAt = LocalDateTime.now().withNano(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(menuId, menu.menuId) && Objects.equals(menuName, menu.menuName) && category == menu.category && Objects.equals(price, menu.price) && Objects.equals(imagePath, menu.imagePath) && Objects.equals(description, menu.description) && Objects.equals(createdAt, menu.createdAt) && Objects.equals(updatedAt, menu.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, menuName, category, price, imagePath, description, createdAt, updatedAt);
    }

    private void checkPrice(Integer price) {
        if (price <= 0) {
            throw new InvalidInputException("메뉴의 가격은 0보다 커야 합니다.", ErrorCode.INVALID_PRICE);
        } else if (price > 100000000) {
            throw new InvalidInputException("메뉴의 가격은 100,000,000보다 작아야 합니다.", ErrorCode.INVALID_PRICE);
        }
    }
}
