package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.Specialization;

import java.util.List;

public interface SpecializationRepo extends GenericCRUD<Specialization> {

    List<Specialization> getAll();
}
