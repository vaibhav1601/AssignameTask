package com.vaibhav.data.mapper;

public interface IMapper<E, D> {

    D mapFromEntity(E e);

    E mapToEntity(D d);

}
