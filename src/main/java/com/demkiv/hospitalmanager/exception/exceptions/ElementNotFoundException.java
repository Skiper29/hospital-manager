package com.demkiv.hospitalmanager.exception.exceptions;

import java.util.NoSuchElementException;

public class ElementNotFoundException extends NoSuchElementException {
    public ElementNotFoundException(String id) {
        super("Not found element with id " + id);
    }
}
