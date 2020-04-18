package com.kodilla.ecommercee.exception;

public enum ExceptionType {
    USER_FOUND("ERROR: User '%s' already exist"),
    USER_NOT_FOUND("ERROR: User with id=%s was not found."),
    USER_BUSY("ERROR: User with id=%s have orders. Can't be deleted."),
    USER_CHANGE_NAME("ERROR: Can't change user name/login on '%s'"),
    ORDER_NOT_FOUND("ERROR: Order with id=%s was not found."),
    ORDER_RESOLVED("ERROR: Order with id=%s was resolved and can't be deleted."),
    ROLE_NOT_FOUND("ERROR: Authority '%s' not found. Only USER and ADMIN are available."),
    CART_NOT_FOUND("ERROR: Cart with id=%s was not found"),
    CART_IS_CLOSED("ERROR: Cart with id=%s is closed for adding new items."),
    ITEM_NOT_FOUND("ERROR: Item with id=%s was not found."),
    PRODUCT_NOT_FOUND("ERROR: Product with id=%s was not found."),
    GROUP_FOUND("ERROR: Group '%s' already exists."),
    GROUP_NOT_FOUND("ERROR: Group with id='%s' was not found.");

    private final String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
