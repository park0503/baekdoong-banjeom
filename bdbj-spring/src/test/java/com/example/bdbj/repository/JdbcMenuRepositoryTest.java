package com.example.bdbj.repository;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JdbcMenuRepositoryTest {
    @Autowired
    JdbcMenuRepository repository;

    static EmbeddedMysql embeddedMysql;

    @BeforeAll
    public void setUp() {
        MysqldConfig config = aMysqldConfig(v5_7_latest)
                .withCharset(UTF8)
                .withPort(2215)
                .withUser("test", "1q2w3e4r")
                .withTimeZone("Asia/Seoul")
                .build();
        embeddedMysql = anEmbeddedMysql(config)
                .addSchema("test-order_mgmt", classPathScript("schema.sql"))
                .start();
    }

    @AfterAll
    public void cleanUp() {
        embeddedMysql.stop();
    }

    @AfterEach
    public void cleanUpEach() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("생성한 객체를 DB에 저장하고 아이디로 찾을 수 있다.")
    public void testSave() {
        Menu menu = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("짜장면")
                .category(Category.MEAL)
                .price(6000)
                .build());

        Optional<Menu> findMenu = repository.findById(menu.getMenuId());
        List<Menu> allMenus = repository.findAll();

        assertThat(findMenu).isNotEmpty();
        assertThat(findMenu.get().getMenuName()).isEqualTo(menu.getMenuName());
    }

    @Test
    @DisplayName("Menu를 이름으로 검색할 수 있다.")
    public void testFindByName() {
        Menu menu0 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("짜장면")
                .category(Category.MEAL)
                .price(6000)
                .build());
        Menu menu1 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("짬뽕")
                .category(Category.MEAL)
                .price(7000)
                .build());
        Menu menu2 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("쟁반짜장")
                .category(Category.MEAL)
                .price(10000)
                .build());

        List<Menu> allMenus = repository.findAll();
        allMenus.forEach((m) -> System.out.println(m.toString()));
        List<Menu> findMenus = repository.findByLikeName("짜장");

        assertThat(allMenus).hasSize(3);
        assertThat(findMenus).hasSize(2);
    }

    @Test
    @DisplayName("메뉴를 카테고리로 검색할 수 있다.")
    public void testFindByCategory() {
        Menu menu0 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("짜장면")
                .category(Category.MEAL)
                .price(6000)
                .build());
        Menu menu1 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("탕수육")
                .category(Category.CUISINE)
                .price(18000)
                .build());
        Menu menu2 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("깐풍기")
                .category(Category.CUISINE)
                .price(20000)
                .build());

        List<Menu> allMenus = repository.findAll();
        List<Menu> findMeals = repository.findByCategory(Category.MEAL);
        List<Menu> findCuisines = repository.findByCategory(Category.CUISINE);

        assertThat(allMenus).hasSize(3);
        assertThat(findMeals).hasSize(1);
        assertThat(findCuisines).hasSize(2);
    }

    @Test
    @DisplayName("생성한 객체를 업데이트 할 수 있따..")
    public void testUpdate() {
        Menu menu = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("짜장면")
                .category(Category.MEAL)
                .price(6000)
                .description("짜장면 입니다.")
                .imagePath("naver.com")
                .build());

        menu.setMenuName("탕수육");
        menu.setPrice(18000);
        menu.setCategory(Category.CUISINE);
        menu.setDescription("짜장면 이었던 것");
        menu.setImagePath("daum.net");
        repository.update(menu);
        Optional<Menu> findMenu = repository.findById(menu.getMenuId());

        assertThat(findMenu).isNotEmpty();
        assertThat(findMenu.get().equals(menu)).isTrue();
    }

    @Test
    @DisplayName("메뉴를 id를 통해 삭제할 수 있다.")
    public void testDeleteById() {
        Menu menu0 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("짜장면")
                .category(Category.MEAL)
                .price(6000)
                .build());
        Menu menu1 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("탕수육")
                .category(Category.CUISINE)
                .price(18000)
                .build());
        Menu menu2 = repository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("깐풍기")
                .category(Category.CUISINE)
                .price(20000)
                .build());

        repository.deleteById(menu0.getMenuId());
        List<Menu> allMenus = repository.findAll();
        Optional<Menu> findMenu = repository.findById(menu0.getMenuId());

        assertThat(findMenu).isEmpty();
        assertThat(allMenus).hasSize(2);
    }
}