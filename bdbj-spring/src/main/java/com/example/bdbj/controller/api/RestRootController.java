package com.example.bdbj.controller.api;


import com.example.bdbj.domain.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/")
@RestController
public class RestRootController {
    @GetMapping("categories")
    public ResponseEntity<Category[]> showCategories() {
        return new ResponseEntity<>(Category.values(), HttpStatus.OK);
    }
}
