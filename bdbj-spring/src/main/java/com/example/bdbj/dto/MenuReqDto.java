package com.example.bdbj.dto;

import com.example.bdbj.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuReqDto {
    private final String menuName;
    private final Category category;
    private final Integer price;
    private final String imagePath;
    private final String description;
}
