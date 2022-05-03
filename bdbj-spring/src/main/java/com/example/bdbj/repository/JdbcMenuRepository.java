package com.example.bdbj.repository;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;
import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.RecordNotUpdatedException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.bdbj.util.JdbcUtils.*;

@Repository
public class JdbcMenuRepository implements MenuRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcMenuRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Menu> menuRowMapper = (resultSet, i) -> {
        UUID menuId = toUUID(resultSet.getBytes("menu_id"));
        String menuName = resultSet.getString("menu_name");
        Category category = Category.valueOf(resultSet.getString("category"));
        Integer price = resultSet.getInt("price");
        String imagePath = resultSet.getString("image_path");
        String description = resultSet.getString("description");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        return new Menu(menuId, menuName, category, price, imagePath, description, createdAt, updatedAt);
    };

    private Map<String, Object> toParamMap(Menu menu) {
        Map paramMap = new HashMap<String, Object>();
        paramMap.put("menuId", menu.getMenuId().toString().getBytes());
        paramMap.put("menuName", menu.getMenuName());
        paramMap.put("category", menu.getCategory().toString());
        paramMap.put("price", menu.getPrice());
        paramMap.put("imagePath", menu.getImagePath());
        paramMap.put("description", menu.getDescription());
        paramMap.put("createdAt", toTimestamp(menu.getCreatedAt()));
        paramMap.put("updatedAt", toTimestamp(menu.getUpdatedAt()));
        return paramMap;
    }

    @Override
    public Menu save(Menu menu) {
        int updated = jdbcTemplate.update(
                "insert into menu(" +
                        "menu_id," +
                        " menu_name," +
                        " category," +
                        " price," +
                        " image_path," +
                        " description," +
                        " created_at," +
                        " updated_at" +
                        ") " +
                "values(" +
                        "UNHEX(REPLACE(:menuId, '-', ''))" +
                        ", :menuName" +
                        ", :category" +
                        ", :price" +
                        ", :imagePath" +
                        ", :description" +
                        ", :createdAt" +
                        ", :updatedAt)"
                , toParamMap(menu));
        if (updated != 1) {
            throw new RecordNotUpdatedException(
                    "Record cant be inserted!"
                    , ErrorCode.MENU_NOT_UPDATED
            );
        }
        return menu;
    }

    @Override
    public List<Menu> findAll() {
        return jdbcTemplate.query("select * from menu", menuRowMapper);
    }

    @Override
    public Optional<Menu> findById(UUID menuId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            "select * from menu where menu_id = UNHEX(REPLACE(:menuId, '-', ''))"
                            , Collections.singletonMap("menuId", menuId.toString().getBytes())
                            , menuRowMapper)
            );
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Menu> findByName(String menuName) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            "select * from menu where menu_name = :menuName"
                            , Collections.singletonMap("menuName", menuName)
                            , menuRowMapper)
            );
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Menu> findByLikeName(String menuName) {
        return jdbcTemplate.query("select * from menu where menu_name like :menuName"
                , Collections.singletonMap("menuName", "%" + menuName + "%")
                , menuRowMapper);
    }

    @Override
    public List<Menu> findByCategory(Category category) {
        return jdbcTemplate.query(
                "select * from menu where category = :category"
                , Collections.singletonMap("category", category.toString())
                , menuRowMapper
        );
    }

    @Override
    public Menu update(Menu menu) {
        int updated = jdbcTemplate.update(
                "update menu set " +
                        "menu_name = :menuName" +
                        ", category = :category" +
                        ", price = :price" +
                        ", image_path = :imagePath" +
                        ", description = :description" +
                        ", updated_at = :updatedAt" +
                        " where menu_id = UNHEX(REPLACE(:menuId, '-', ''))"
                , toParamMap(menu));
        if (updated != 1) {
            throw new RecordNotUpdatedException(
                    "Record cant be updated!"
                    , ErrorCode.MENU_NOT_UPDATED
            );
        }
        return menu;
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from menu", Collections.emptyMap());
    }

    @Override
    public void deleteById(UUID menuId) {
        jdbcTemplate.update(
                "delete from menu where menu_id = UNHEX(REPLACE(:menuId, '-', ''))"
                , Collections.singletonMap("menuId", menuId.toString().getBytes())
        );
    }
}
