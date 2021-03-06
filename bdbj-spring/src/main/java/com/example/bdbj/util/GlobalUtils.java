package com.example.bdbj.util;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.OrderStatus;
import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.FieldBlankException;
import com.example.bdbj.exception.InvalidInputException;

import java.util.UUID;

public class GlobalUtils {
    public static UUID convertStringToUUID(String stringId) {
        UUID id;
        try {
            id = UUID.fromString(stringId);
        }catch (IllegalArgumentException ex) {
            throw new InvalidInputException("Invalid Id format.", ErrorCode.INVALID_UUID_FORMAT);
        }
        return id;
    }

    public static void validateNotBlank(String input, String valueName) {
        if (input.isBlank()) {
            throw new FieldBlankException(valueName + " is required!", ErrorCode.FIELD_BLANK);
        }
    }

    public static Category convertStringToCategory(String input) {
        try {
            return Category.valueOf(input);
        } catch (IllegalArgumentException ex) {
            throw new InvalidInputException("Invalid category.", ErrorCode.INVALID_CATEGORY);
        }
    }

    public static OrderStatus convertStringToOrderStatus(String input) {
        try {
            return OrderStatus.valueOf(input);
        } catch (IllegalArgumentException ex) {
            throw new InvalidInputException("Invalid category.", ErrorCode.INVALID_ORDER_STATUS);
        }
    }

    public static void checkNull(Object object) {
        if (object == null) {
            throw new FieldBlankException("입력값이 부족합니다.", ErrorCode.FIELD_BLANK);
        }
    }

    public static void checkMenuNull(UUID menuId, String menuName, Category category, Integer price) {
        if (menuId == null || price == null || menuName == null || category == null)  {
            throw new FieldBlankException("입력값이 부족합니다.", ErrorCode.FIELD_BLANK);
        }
    }
}
