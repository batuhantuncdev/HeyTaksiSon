package com.heytaksi.heytaksibackend.controller;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BaseController {
    public static final String APP_USER = "appuser";
    public static final String REGISTER = "register";
    public static final String LOGIN = "login";

    public static final String BASE_PATH = "/api/";

    static final String HAS_ADMIN_OR_SUPER_ROLE = "hasRole('ADMIN') or hasRole('SUPER')";
    static final String ALL_ROLE = "hasRole('ROLE_SUPER') or hasRole('DRIVER') or hasRole('CUSTOMER')";

    private final ModelMapper mapper;

    public BaseController(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public <S, D> D map(S sourceInstance, Class<D> destinationTypeClass) {
        if (!ObjectUtils.isEmpty(mapper)
                && !ObjectUtils.isEmpty(mapper.getConfiguration())) {
            mapper.getConfiguration().setAmbiguityIgnored(true);
        }
        return mapper.map(sourceInstance, destinationTypeClass);
    }

    <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        if (!ObjectUtils.isEmpty(mapper)
                && !ObjectUtils.isEmpty(mapper.getConfiguration())) {
            mapper.getConfiguration().setAmbiguityIgnored(true);
        }
        return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
    }

    public <S, T> Page<T> mapPage(Page<S> source, Class<T> targetClass) {
        List<S> sourceList = source.getContent();

        List<T> list = new ArrayList<>();
        for (S s : sourceList) {
            T target = mapper.map(s, targetClass);
            list.add(target);
        }

        return new PageImpl<>(list,
                PageRequest.of(source.getNumber(), source.getSize(), source.getSort()),
                source.getTotalElements());
    }
}
