package com.alexandertutoriales.ecommerce.service.mapper;

import com.alexandertutoriales.ecommerce.service.entity.Categoria;
import com.alexandertutoriales.ecommerce.service.entity.dto.CategoriaDto;
import com.alexandertutoriales.ecommerce.service.mapperImpl.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends GenericMapper<CategoriaDto, Categoria> {
    @Override
    CategoriaDto toDto(Categoria categoria);
    @Override
    Categoria toEntity(CategoriaDto categoriaDto);
}
