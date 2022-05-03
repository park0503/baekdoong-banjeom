package com.example.bdbj.service;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;
import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.RecordNotFoundException;
import com.example.bdbj.exception.ValueDuplicationException;
import com.example.bdbj.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu getMenuById(UUID menuId) {
        return menuRepository.findById(menuId).orElseThrow(
                () -> new RecordNotFoundException(
                        "해당 이름의 menu가 없습니다."
                        , ErrorCode.MENU_NOT_FOUND)
        );
    }

    public List<Menu> getMenusByCategory(Category category) {
        return menuRepository.findByCategory(category);
    }

    public List<Menu> searchMenusByName(String keyword) {
        return menuRepository.findByLikeName(keyword);
    }

    public Menu getMenuByName(String menuName) {
        return menuRepository.findByName(menuName)
                .orElseThrow(
                        () -> new RecordNotFoundException(
                                "해당 이름의 menu가 없습니다."
                                , ErrorCode.MENU_NOT_FOUND)
                );
    }

    public Menu createMenu(String menuName, Category category, Integer price, String imagePath, String description) {
        Optional<Menu> temp = menuRepository.findByName(menuName);
        if(temp.isPresent()) {
            throw new ValueDuplicationException("메뉴 이름이 중복됩니다.", ErrorCode.MENU_NAME_DUPLICATION);
        }
        return menuRepository.save(
                Menu.builder()
                        .menuId(UUID.randomUUID())
                        .menuName(menuName)
                        .category(category)
                        .price(price)
                        .imagePath(imagePath)
                        .description(description)
                        .build()
        );
    }

    public Menu updateMenu(UUID menuId, String menuName, Category category, Integer price, String imagePath, String description) {
        Menu menu = getMenuById(menuId);
        if (!menu.getMenuName().equals(menuName)) {
            Optional<Menu> temp = menuRepository.findByName(menuName);
            if(temp.isPresent()) {
                throw new ValueDuplicationException("메뉴 이름이 중복됩니다.", ErrorCode.MENU_NAME_DUPLICATION);
            }
            menu.setMenuName(menuName);
        }
        if (!menu.getCategory().equals(category)) {
            menu.setCategory(category);
        }
        if (!menu.getPrice().equals(price)) {
            menu.setPrice(price);
        }
        if(!Objects.equals(menu.getImagePath(), imagePath)) {
            menu.setImagePath(imagePath);
        }
        if (!Objects.equals(menu.getDescription(), description)) {
            menu.setDescription(description);
        }
        return menuRepository.update(menu);
    }

    public void removeMenuById(UUID menuId) {
        menuRepository.deleteById(menuId);
    }

    public void removeAllMenu() {
        menuRepository.deleteAll();
    }
}
