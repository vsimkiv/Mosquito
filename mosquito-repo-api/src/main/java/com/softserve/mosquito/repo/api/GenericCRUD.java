package com.softserve.mosquito.repo.api;

import java.io.Serializable;

public interface GenericCRUD<E extends Serializable> {

    E create(E e);

    E read(Long id);

    E update(E e);

    void delete(Long id);
}
