package com.example.bdbj.repository;

import com.example.bdbj.domain.*;
import com.example.bdbj.domain.Order;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JdbcOrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MenuRepository menuRepository;

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
        orderRepository.deleteAll();
        menuRepository.deleteAll();
    }

    @Test
    @DisplayName("생성한 주문을 DB에 저장하고 아이디로 찾을 수 있다.")
    public void testSave() {
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
        Address address = new Address("경기도 수원시 원천동 28-37", "나동 302호", "16502");
        List<OrderItem> orderItems = new ArrayList<>();
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
        orderItems.add(orderItem0);
        orderItems.add(orderItem1);
        Order order = Order.builder().
                orderId(UUID.randomUUID()).
                phoneNumber("119").
                address(address).
                orderItems(orderItems).
                build();
        orderRepository.save(order);

        Optional<Order> findOrder = orderRepository.findById(order.getOrderId());

        assertThat(findOrder).isNotEmpty();
        assertThat(findOrder.get()).extracting(
                "orderId",
                "phoneNumber",
                "createdAt"
        ).containsExactly(
                order.getOrderId(),
                order.getPhoneNumber(),
                order.getCreatedAt());
        assertThat(findOrder.get().getAddress()).extracting(
                "address",
                "detailedAddress",
                "postcode"
        ).containsExactly(
                "경기도 수원시 원천동 28-37",
                "나동 302호",
                "16502"
        );
        assertThat(findOrder.get().getOrderItems()).hasSize(2);
    }

    @Test
    @DisplayName("생성한 주문을 DB에 저장하고 전화번호로 찾을 수 있다.")
    public void testSaveAndFindByPhoneNumber() {
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
        Address address = new Address("경기도 수원시 원천동 28-37", "나동 302호", "16502");
        List<OrderItem> orderItems = new ArrayList<>();
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
        orderItems.add(orderItem0);
        orderItems.add(orderItem1);
        Order order = Order.builder().
                orderId(UUID.randomUUID()).
                phoneNumber("119").
                address(address).
                orderItems(orderItems).
                build();
        orderRepository.save(order);

        List<Order> orders = orderRepository.findByPhoneNumber(order.getPhoneNumber());

        assertThat(orders).hasSize(1);
    }

    @Test
    @DisplayName("주문을 업데이트 할 수 있다.")
    public void testUpdate() {
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
        Address address = new Address("경기도 수원시 원천동 28-37", "나동 302호", "16502");
        List<OrderItem> orderItems = new ArrayList<>();
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
        orderItems.add(orderItem0);
        orderItems.add(orderItem1);
        Order order = Order.builder().
                orderId(UUID.randomUUID()).
                phoneNumber("119").
                address(address).
                orderItems(orderItems).
                build();
        orderRepository.save(order);

        order.setOrderStatus(OrderStatus.ORDERED);
        orderRepository.update(order);
        Optional<Order> findOrder = orderRepository.findById(order.getOrderId());

        assertThat(findOrder).isNotEmpty();
        assertThat(findOrder.get()).extracting(
                "orderStatus",
                "updatedAt"
        ).containsExactly(
                order.getOrderStatus(),
                order.getUpdatedAt()
        );
    }

}