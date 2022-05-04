package com.example.bdbj.controller.api;

import com.example.bdbj.dto.MenuResDto;
import com.example.bdbj.dto.SimpleMenuResDto;
import com.example.bdbj.service.MenuService;
import com.example.bdbj.util.GlobalUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuRestController {
    private final MenuService menuService;

    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("")
    public ResponseEntity<List<SimpleMenuResDto>> showMenus() {
        return new ResponseEntity<>(
                menuService
                        .getAllMenus()
                        .stream()
                        .map(SimpleMenuResDto::new)
                        .collect(Collectors.toList()
                        ), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResDto> showMenu(@PathVariable String id) {
        UUID menuId = GlobalUtils.convertStringToUUID(id);
        return new ResponseEntity<>(
                new MenuResDto(menuService.getMenuById(menuId)
                ), HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<SimpleMenuResDto>> searchMenus(@RequestParam(required = false) String name) {
        return new ResponseEntity<>(
                menuService
                        .searchMenusByName(name)
                        .stream()
                        .map(SimpleMenuResDto::new)
                        .collect(Collectors.toList()
                        ), HttpStatus.OK
        );
    }
}
