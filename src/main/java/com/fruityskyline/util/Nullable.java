package com.fruityskyline.util;

import java.util.function.Function;

public class Nullable<T> {
    private final T value;
    private Nullable(T value){
        this.value = value;
    }

    public static <T> Nullable<T> of(T value){
        if(value == null){
            throw new IllegalArgumentException("Nullable.of() cannot accept null. Use ofNullable() instead.");
        }
        return new Nullable<>(value);
    }

    public static <T> Nullable<T> ofNullable(T value){
        return new Nullable<>(value);
    }

    public boolean hasValue(){
        return value != null;
    }

    public T getValue(){
        if(value == null){
            throw new IllegalStateException("Value is null");
        }
        return value;
    }

    public T getValueOrDefault(T defaultValue){
        return (value != null) ? value : defaultValue;
    }

    public <U> Nullable<U> map(Function<? super T, ? extends U> mapper){
        if(!hasValue()){
            return Nullable.ofNullable(null);
        }
        return Nullable.ofNullable(mapper.apply(value));
    }

    @Override
    public String toString(){
        return hasValue() ? "Nullable[" + value + "]" : "Nullable.empty";
    }
}
