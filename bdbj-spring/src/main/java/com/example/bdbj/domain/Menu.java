package com.example.bdbj.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Menu {
    private final UUID menuId;
    private String menuName;
    private Category category;
    private Integer price;
    private String imagePath;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Menu(@NonNull UUID menuId, @NonNull Integer price, @NonNull String menuName, @NonNull Category category, String imagePath) {
        this.menuId = menuId;
        this.price = price;
        this.menuName = menuName;
        this.category = category;
        this.imagePath = imagePath;
        this.createdAt = LocalDateTime.now().withNano(0);
    }
}
