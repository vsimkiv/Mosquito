package com.softserve.mosquito.repo.api;

import java.io.Serializable;
import java.util.List;

public interface GenericCRUD<E extends Serializable> {
    E create(E e);

    E read(Long id);

    E update(E e);

    void delete(Long id);

    List<E> readAll();
}
