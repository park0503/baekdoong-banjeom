package com.example.bdbj.repository;

import com.example.bdbj.domain.Address;
import com.example.bdbj.domain.Order;
import com.example.bdbj.domain.OrderStatus;
import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.RecordNotUpdatedException;
import com.example.bdbj.util.GlobalUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.bdbj.util.JdbcUtils.toLocalDateTime;
import static com.example.bdbj.util.JdbcUtils.toUUID;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Order> orderRowMapper = (resultSet, i) -> {
        UUID orderId = toUUID(resultSet.getBytes("order_id"));
        String phoneNumber = resultSet.getString("phone_number");
        String address = resultSet.getString("address");
        String detailedAddress = resultSet.getString("detailed_address");
        String postcode = resultSet.getString("postcode");
        OrderStatus orderStatus = GlobalUtils.convertStringToOrderStatus(resultSet.getString("order_status"));
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));
        Address addressVo = new Address(address, detailedAddress, postcode);
        return new Order(orderId, phoneNumber, addressVo, orderStatus, createdAt, updatedAt);
    };

    private Map<String, Object> toOrderParamMap(Order order) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", order.getOrderId().toString().getBytes());
        paramMap.put("phoneNumber", order.getPhoneNumber());
        paramMap.put("address", order.getAddress().getAddress());
        paramMap.put("detailedAddress", order.getAddress().getDetailedAddress());
        paramMap.put("postcode", order.getAddress().getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());
        return paramMap;
    }

//    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderItem item) {
//        var paramMap = new HashMap<String, Object>();
//        paramMap.put("orderId", orderId.toString().getBytes());
//        paramMap.put("productId", item.getProductId().toString().getBytes());
//        paramMap.put("category", item.getCategory().toString());
//        paramMap.put("price", item.getPrice());
//        paramMap.put("quantity", item.getQuantity());
//        paramMap.put("createdAt", createdAt);
//        paramMap.put("updatedAt", updatedAt);
//        return paramMap;
//    }

    @Override
    public Order save(Order order) {
        int updated = jdbcTemplate.update(
                "insert into orders(order_id, phone_number, address, detailed_address, postcode, order_status, created_at, updated_at) values(UNHEX(REPLACE(:orderId, '-', '')), :phoneNumber, :address, :detailedAddress, :postcode, :orderStatus, :createdAt, :updatedAt)", toOrderParamMap(order));
        if (updated != 1) {
            throw new RecordNotUpdatedException(
                    "Order record cant be inserted!"
                    , ErrorCode.ORDER_NOT_UPDATED
            );
        }
        return order;
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query("select * from orders", orderRowMapper);
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(
                            "select * from orders where order_id = UNHEX(REPLACE(:orderId, '-', ''))"
                            , Collections.singletonMap("orderId", orderId.toString().getBytes())
                            , orderRowMapper)
            );
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findByPhoneNumber(String phoneNumber) {
        return jdbcTemplate.query(
                "select * from orders where phone_number = :phoneNumber"
                , Collections.singletonMap("phoneNumber", phoneNumber)
                , orderRowMapper
        );
    }

    @Override
    public Order update(Order order) {
        int updated = jdbcTemplate.update(
                "update orders set order_status = :orderStatus, updated_at = :updatedAt where order_id = UNHEX(REPLACE(:orderId, '-', ''))"
                , toOrderParamMap(order));
        if (updated != 1) {
            throw new RecordNotUpdatedException(
                    "Order record cant be updated!"
                    , ErrorCode.ORDER_NOT_UPDATED
            );
        }
        return order;
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from orders", Collections.emptyMap());
    }
}
