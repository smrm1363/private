package com.mohammadreza.mirali.energyconsumption.domain.common;

import java.io.IOException;

/**
 * Created by mmirali on 14/10/2018.
 */
@FunctionalInterface
public interface ValidationRule<T> {
    void validate(T t) throws ValidationException;

}
