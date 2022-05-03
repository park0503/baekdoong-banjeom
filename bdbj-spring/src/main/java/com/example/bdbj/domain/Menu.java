package com.example.bdbj.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@ToString
@Getter
@AllArgsConstructor
public class Menu {
    private final UUID menuId;
    private String menuName;
    private Category category;
    private Integer price;
    private String imagePath;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Menu(@NonNull UUID menuId, @NonNull Integer price, @NonNull String menuName, @NonNull Category category, String imagePath, String description) {
        this.menuId = menuId;
        this.price = price;
        this.menuName = menuName;
        this.category = category;
        this.imagePath = imagePath;
        this.description = description;
        this.createdAt = LocalDateTime.now().withNano(0);
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
        updatedUpdatedAt();
    }

    public void setCategory(Category category) {
        this.category = category;
        updatedUpdatedAt();
    }

    public void setPrice(Integer price) {
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
}