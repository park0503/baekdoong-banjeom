package com.example.bdbj.dto;

import com.example.bdbj.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMenuReqDto {
    private String menuName;
    private Category category;
    private Integer price;
    private String imagePath;
    private String description;
}
