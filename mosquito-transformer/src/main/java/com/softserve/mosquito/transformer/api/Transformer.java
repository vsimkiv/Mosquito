package com.softserve.mosquito.transformer.api;

public interface Transformer<E,D> {

    E toEntity(D d);
    D toDTO(E e);

}
