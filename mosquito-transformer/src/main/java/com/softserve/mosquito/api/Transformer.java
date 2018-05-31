package com.softserve.mosquito.api;

public interface Transformer<E,D> {

    E toEntity(D d);
    D toDTO(E e);

}
