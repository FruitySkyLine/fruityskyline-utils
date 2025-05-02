package com.fruityskyline.util.result;

import java.util.function.Function;

public class Result<T,E> {
    private final T success;
    private final E error;
    private Result(T success, E error){
        this.success = success;
        this.error = error;
    }

    // Méthodes
    public static <T,E> Result<T,E> success(T value){
        return new Result<>(value, null);
    }
    public static <T,E> Result<T,E> error(E error){
        return new Result<>(null, error);
    }
    public boolean isSuccess(){
        return success != null;
    }
    public boolean isError(){
        return error != null;
    }
    public T getSuccess(){
        if(!isSuccess()){
            throw new IllegalStateException("Result is not a success");
        }
        return success;
    }
    public E getError(){
        if(!isError()){
            throw new IllegalStateException("Result is not an error");
        }
        return error;
    }
    public <U> Result<U,E> map(Function<? super T, ? extends U> mapper){
        if(isError()){
            return Result.error(error);
        }
        return Result.success(mapper.apply(success));
    }
}
