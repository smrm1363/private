package com.mohammadreza.mirali.energyconsumption.domain.common;


@FunctionalInterface
public interface ValidationRule<T> {
    void validate(T t) throws ValidationException;

}
