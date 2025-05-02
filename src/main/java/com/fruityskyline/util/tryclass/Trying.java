package com.fruityskyline.util.tryclass;

import java.util.function.Function;
import java.util.function.Supplier;

public class Trying<T> {
    private final T value;
    private final Throwable error;
    private Trying(final T value, final Throwable error) {
        this.value = value;
        this.error = error;
    }

    // Méthodes
    public static <T> Trying<T> success(final T value) {
        return new Trying<>(value, null);
    }
    public static <T> Trying<T> failure(final Throwable error) {
        return new Trying<>(null, error);
    }
    public static <T> Trying<T> of(Supplier<T> supplier){
        try {
            return success(supplier.get());
        } catch (Throwable e) {
            return failure(e);
        }
    }
    public boolean isSuccess(){
        return value != null;
    }
    public boolean isFailure(){
        return error != null;
    }
    public T get(){
        if(!isSuccess()){
            throw new IllegalStateException("Trying is not a success");
        }
        return value;
    }
    public Throwable getException(){
        if(!isFailure()){
            throw new IllegalStateException("Trying is not a failure");
        }
        return error;
    }
    public <U> Trying<U> map(Function<? super T, ? extends U> mapper){
        if(isFailure()){
            return failure(error);
        }
        try {
            return success(mapper.apply(value));
        } catch (Throwable e) {
            return failure(e);
        }
    }
}
