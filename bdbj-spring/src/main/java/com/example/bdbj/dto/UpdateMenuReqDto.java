package com.example.bdbj.dto;

import com.example.bdbj.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateMenuReqDto {
    private UUID menuId;
    private String menuName;
    private Category category;
    private Integer price;
    private String imagePath;
    private String description;
}
