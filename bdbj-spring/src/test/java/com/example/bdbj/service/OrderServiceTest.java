package com.example.bdbj.service;

import com.example.bdbj.domain.*;
import com.example.bdbj.domain.Order;
import com.example.bdbj.repository.MenuRepository;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    MenuRepository menuRepository;

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
        orderService.removeAllOrder();
    }

    @Test
    @DisplayName("주문을 생성할 수 있다.")
    public void testCreate() {
        Menu menu0 = menuRepository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("짜장면")
                .category(Category.MEAL)
                .price(6000)
                .build());
        Menu menu1 = menuRepository.save(Menu.builder()
                .menuId(UUID.randomUUID())
                .menuName("짬뽕")
                .category(Category.MEAL)
                .price(7000)
                .build());
        OrderItem orderItem0 = OrderItem.builder().
                menuId(menu0.getMenuId()).
                category(menu0.getCategory()).
                price(menu0.getPrice()).
                build();
        OrderItem orderItem1 = OrderItem.builder().
                menuId(menu1.getMenuId()).
                category(menu1.getCategory()).
                price(menu1.getPrice()).
                build();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem0);
        orderItems.add(orderItem1);
        Address address = new Address("경기도 수원시 원천동 28-37",
                "나동 302호",
                "16502");
        Order order = orderService.createOrder(
                "119",
                address,
                orderItems
        );

        assertThat(order.getPhoneNumber()).isEqualTo("119");
        assertThat(order.getAddress()).extracting(
                "address",
                "detailedAddress",
                "postcode"
        ).containsExactly(
                "경기도 수원시 원천동 28-37",
                "나동 302호",
                "16502"
        );
        assertThat(order.getOrderItems()).hasSize(2);
    }
}