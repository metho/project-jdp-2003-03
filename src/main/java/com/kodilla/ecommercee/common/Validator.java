package com.kodilla.ecommercee.common;

import com.kodilla.ecommercee.exception.AuthorizationDeniedException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final String NOT_AUTHORISED = "ERROR: User is blocked or key not found.";
    private static final String KEY = "1234";

    public void validateUser(String key) {
        /**
         in the future add User blocked check and key validation before DML
         **/
        if (!key.equals(KEY)) throw new AuthorizationDeniedException(NOT_AUTHORISED);
    }
}
