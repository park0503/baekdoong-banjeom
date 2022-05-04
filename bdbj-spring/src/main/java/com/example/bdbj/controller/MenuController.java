package com.example.bdbj.controller;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;
import com.example.bdbj.dto.MenuReqDto;
import com.example.bdbj.service.MenuService;
import com.example.bdbj.util.GlobalUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("")
    public String showMenus(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        return "menus/menus";
    }

    @GetMapping("/{menuId}")
    public String showMenu(@PathVariable String menuId, Model model) {
        UUID id = GlobalUtils.convertStringToUUID(menuId);
        Menu menu = menuService.getMenuById(id);
        model.addAttribute("menu", menu);
        return "menus/show";
    }

    @GetMapping("/new")
    public String showCreateMenuPage(Model model) {
        model.addAttribute("categories", Category.values());
        return "menus/new";
    }

    @PostMapping("/new")
    public String createMenu(MenuReqDto menuReqDto) {
        Menu menu = menuService.createMenu(
                menuReqDto.getMenuName(),
                menuReqDto.getCategory(),
                menuReqDto.getPrice(),
                menuReqDto.getImagePath(),
                menuReqDto.getDescription()
        );
        return "redirect:/menus/" + menu.getMenuId();
    }

    @GetMapping("/{menuId}/edit")
    public String showUpdateMenuPage(@PathVariable String menuId, Model model) {
        UUID id = GlobalUtils.convertStringToUUID(menuId);
        Menu menu = menuService.getMenuById(id);
        model.addAttribute("menu", menu);
        model.addAttribute("categories", Category.values());
        return "menus/edit";
    }

    @PostMapping("/{menuId}/edit")
    public String updateMenu(MenuReqDto menuReqDto, @PathVariable String menuId) {
        UUID id = GlobalUtils.convertStringToUUID(menuId);
        Menu menu = menuService.updateMenu(
                id,
                menuReqDto.getMenuName(),
                menuReqDto.getCategory(),
                menuReqDto.getPrice(),
                menuReqDto.getImagePath(),
                menuReqDto.getDescription()
        );
        return "redirect:/menus/" + menu.getMenuId();
    }

    @PostMapping("/{menuId}/delete")
    public String deleteMenu(@PathVariable String menuId) {
        UUID id = GlobalUtils.convertStringToUUID(menuId);
        menuService.removeMenuById(id);
        return "redirect:/menus";
    }

}
