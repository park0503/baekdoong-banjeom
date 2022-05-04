package com.example.bdbj.service;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.Menu;
import com.example.bdbj.exception.RecordNotFoundException;
import com.example.bdbj.exception.ValueDuplicationException;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MenuServiceTest {
    @Autowired
    MenuService service;

    EmbeddedMysql embeddedMysql;

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
        service.removeAllMenu();
    }

    @Test
    @DisplayName("새로운 Menu를 생성할 수 있다.")
    public void testCreateMenu() {
        Menu menu = service.createMenu("짜장면", Category.MEAL, 6000, "naver.com", null);

        assertThat(menu)
                .extracting(
                        "menuName"
                        , "category"
                        , "price"
                        , "imagePath"
                        , "description"
                ).containsExactly(
                        "짜장면"
                        , Category.MEAL
                        , 6000
                        , "naver.com"
                        , null);
    }

    @Test
    @DisplayName("중복된 이름으로 메뉴를 생성할 수 없다.")
    public void testCreateMenuUsingDuplicatedMenuName() {
        Menu menu = service.createMenu("짜장면", Category.MEAL, 6000, "naver.com", null);

        assertThatThrownBy(
                () -> service.createMenu("짜장면", Category.MEAL, 7000, "daum.net", "중복"))
                .isInstanceOf(ValueDuplicationException.class);
    }

    @Test
    @DisplayName("잘못된 아이디로 menu 를 찾을 수 없다.")
    public void testGetMenuByInvalidId() {
        assertThatThrownBy(
                () -> service.getMenuById(UUID.randomUUID()))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    @DisplayName("특정 아이디로 menu를 찾을 수 있다.")
    public void testGetMenuById() {
        Menu menu = service.createMenu("짜장면", Category.MEAL, 6000, "naver.com", null);
        service.createMenu("짬뽕", Category.MEAL, 7000, "naver.com", null);

        Menu findMenu = service.getMenuById(menu.getMenuId());

        assertThat(findMenu.equals(menu)).isTrue();
    }

    @Test
    @DisplayName("특정 이름으로 menu를 찾을 수 있다.")
    public void testGetMenuByMenuName() {
        Menu menu = service.createMenu("짜장면", Category.MEAL, 6000, "naver.com", null);
        service.createMenu("짬뽕", Category.MEAL, 7000, "naver.com", null);

        Menu findMenu = service.getMenuByName("짜장면");

        assertThat(findMenu.equals(menu)).isTrue();
    }

    @Test
    @DisplayName("잘못된 이름으로 menu 를 찾을 수 없다.")
    public void testGetMenuByInvalidMenuName() {
        service.createMenu("짜장면", Category.MEAL, 6000, "naver.com", null);
        service.createMenu("짬뽕", Category.MEAL, 7000, "naver.com", null);

        assertThatThrownBy(
                () -> service.getMenuByName("탕수육"))
                .isInstanceOf(RecordNotFoundException.class);
    }

    @Test
    @DisplayName("메뉴를 업데이트 할 수 있다.")
    public void testUpdateMenu() {
        Menu menu = service.createMenu("짜장면", Category.MEAL, 6000, "naver.com", null);
        Menu updatedMenu = service.updateMenu(menu.getMenuId(), "탕수육", Category.CUISINE, 18000, "naver.com", "짜장면 이었던 것");

        assertThat(updatedMenu)
                .extracting(
                        "menuName"
                        , "category"
                        , "price"
                        , "imagePath"
                        , "description"
                ).containsExactly(
                        "탕수육"
                        , Category.CUISINE
                        , 18000
                        , "naver.com"
                        , "짜장면 이었던 것");
    }

    @Test
    @DisplayName("중복된 이름으로 메뉴를 업데이트 할 수 없다.")
    public void testUpdateMenuUsingDuplicatedMenuName() {
        Menu menu0 = service.createMenu("짜장면", Category.MEAL, 6000, "naver.com", null);
        Menu menu1 = service.createMenu("탕수육", Category.CUISINE, 18000, "naver.com", null);

        assertThatThrownBy(
                () -> service.updateMenu(menu0.getMenuId(), "탕수육", Category.CUISINE, 18000, "naver.com", "짜장면 이었던 것"))
                .isInstanceOf(ValueDuplicationException.class);
    }
}