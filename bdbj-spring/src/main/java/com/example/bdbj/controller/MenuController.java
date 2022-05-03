package com.example.bdbj.controller;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;
import com.example.bdbj.dto.CreateMenuReqDto;
import com.example.bdbj.dto.UpdateMenuReqDto;
import com.example.bdbj.service.MenuService;
import com.example.bdbj.util.GlobalUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menus")
    public String showMenus(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        return "menus/menus";
    }

    @GetMapping("/menus/{menuId}")
    public String showMenu(@PathVariable String menuId, Model model) {
        UUID id = GlobalUtils.convertStringToUUID(menuId);
        Menu menu = menuService.getMenuById(id);
        model.addAttribute("menu", menu);
        return "menus/show";
    }

    @GetMapping("/menus/new")
    public String showCreateMenuPage(Model model) {
        model.addAttribute("categories", Category.values());
        return "menus/new";
    }

    @PostMapping("/menus/new")
    public String createMenu(CreateMenuReqDto createMenuReqDto) {
        Menu menu = menuService.createMenu(
                createMenuReqDto.getMenuName(),
                createMenuReqDto.getCategory(),
                createMenuReqDto.getPrice(),
                createMenuReqDto.getImagePath(),
                createMenuReqDto.getDescription()
        );
        return "redirect:/menus/" + menu.getMenuId();
    }

    @GetMapping("/menus/{menuId}/edit")
    public String showUpdateMenuPage(@PathVariable String menuId, Model model) {
        UUID id = GlobalUtils.convertStringToUUID(menuId);
        Menu menu = menuService.getMenuById(id);
        model.addAttribute("menu", menu);
        model.addAttribute("categories", Category.values());
        return "menus/edit";
    }

    @PostMapping("/menus/{menuId}/edit")
    public String updateMenu(CreateMenuReqDto createMenuReqDto, @PathVariable String menuId) {
        UUID id = GlobalUtils.convertStringToUUID(menuId);
        Menu menu = menuService.updateMenu(
                id,
                createMenuReqDto.getMenuName(),
                createMenuReqDto.getCategory(),
                createMenuReqDto.getPrice(),
                createMenuReqDto.getImagePath(),
                createMenuReqDto.getDescription()
        );
        return "redirect:/menus/" + menu.getMenuId();
    }

    @PostMapping("/menus/{menuId}/delete")
    public String deleteMenu(@PathVariable String menuId) {
        UUID id = GlobalUtils.convertStringToUUID(menuId);
        menuService.removeMenuById(id);
        return "redirect:/menus";
    }

}
