package com.example.bdbj.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class Menu {
    private final UUID menuId;
    private Integer price;
    private String name;
    private Category category;
    private String imagePath;

    @Builder
    public Menu(@NonNull UUID menuId, @NonNull Integer price, @NonNull String name, @NonNull Category category, String imagePath) {
        this.menuId = menuId;
        this.price = price;
        this.name = name;
        this.category = category;
        this.imagePath = imagePath;
    }
}
