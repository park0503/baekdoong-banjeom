package com.example.bdbj.repository;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenuRepository {
    Menu save(Menu menu);

    List<Menu> findAll();

    Optional<Menu> findById(UUID menuId);

    Optional<Menu> findByName(String menuName);

    List<Menu> findByLikeName(String menuName);

    List<Menu> findByCategory(Category category);

    Menu update(Menu menu);

    void deleteAll();

    void deleteById(UUID menuId);
}
