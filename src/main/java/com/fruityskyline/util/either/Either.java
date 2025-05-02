package com.fruityskyline.util.either;

import java.util.function.Function;

public class Either<L,R> {
    private final L left;
    private final R right;
    private Either(L left, R right){
        this.left = left;
        this.right = right;
    }

    // Méthodes
    public static <L,R> Either<L,R> left(L value){
        return new Either<>(value, null);
    }
    public static <L,R> Either<L,R> right(R value){
        return new Either<>(null, value);
    }
    public boolean isLeft(){
        return left != null;
    }
    public boolean isRight(){
        return right != null;
    }
    public L getLeft(){
        if(!isLeft()){
            throw new IllegalStateException("Either is not a left");
        }
        return left;
    }
    public R getRight(){
        if(!isRight()){
            throw new IllegalStateException("Either is not a right");
        }
        return right;
    }
    public <U> Either<U,R> mapLeft(Function<? super L, ? extends U> mapper){
        if(isRight()){
            return Either.right(right);
        }
        return Either.left(mapper.apply(left));
    }
    public <U> Either<L,U> mapRight(Function<? super R, ? extends U> mapper){
        if(isLeft()){
            return Either.left(left);
        }
        return Either.right(mapper.apply(right));
    }
}
