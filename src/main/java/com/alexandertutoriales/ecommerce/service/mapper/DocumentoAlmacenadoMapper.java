package com.alexandertutoriales.ecommerce.service.mapper;

import com.alexandertutoriales.ecommerce.service.entity.DocumentoAlmacenado;
import com.alexandertutoriales.ecommerce.service.entity.dto.DocumentoAlmacenadoDto;
import com.alexandertutoriales.ecommerce.service.mapperImpl.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentoAlmacenadoMapper extends GenericMapper<DocumentoAlmacenadoDto, DocumentoAlmacenado> {
    @Override
    DocumentoAlmacenadoDto toDto(DocumentoAlmacenado documentoAlmacenado);
    @Override
    DocumentoAlmacenado toEntity(DocumentoAlmacenadoDto documentoAlmacenadoDto);
}
