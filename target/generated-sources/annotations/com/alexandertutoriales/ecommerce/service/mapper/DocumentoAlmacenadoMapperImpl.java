package com.alexandertutoriales.ecommerce.service.mapper;

import com.alexandertutoriales.ecommerce.service.entity.DocumentoAlmacenado;
import com.alexandertutoriales.ecommerce.service.entity.dto.DocumentoAlmacenadoDto;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-16T17:51:23-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class DocumentoAlmacenadoMapperImpl implements DocumentoAlmacenadoMapper {

    @Override
    public List<DocumentoAlmacenado> toEntity(List<DocumentoAlmacenadoDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<DocumentoAlmacenado> list = new ArrayList<DocumentoAlmacenado>( dtoList.size() );
        for ( DocumentoAlmacenadoDto documentoAlmacenadoDto : dtoList ) {
            list.add( toEntity( documentoAlmacenadoDto ) );
        }

        return list;
    }

    @Override
    public List<DocumentoAlmacenadoDto> toDto(List<DocumentoAlmacenado> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DocumentoAlmacenadoDto> list = new ArrayList<DocumentoAlmacenadoDto>( entityList.size() );
        for ( DocumentoAlmacenado documentoAlmacenado : entityList ) {
            list.add( toDto( documentoAlmacenado ) );
        }

        return list;
    }

    @Override
    public Set<DocumentoAlmacenadoDto> toDto(Set<DocumentoAlmacenado> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<DocumentoAlmacenadoDto> set = new LinkedHashSet<DocumentoAlmacenadoDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( DocumentoAlmacenado documentoAlmacenado : entityList ) {
            set.add( toDto( documentoAlmacenado ) );
        }

        return set;
    }

    @Override
    public DocumentoAlmacenadoDto toDto(DocumentoAlmacenado documentoAlmacenado) {
        if ( documentoAlmacenado == null ) {
            return null;
        }

        DocumentoAlmacenadoDto documentoAlmacenadoDto = new DocumentoAlmacenadoDto();

        documentoAlmacenadoDto.setId( documentoAlmacenado.getId() );
        documentoAlmacenadoDto.setNombre( documentoAlmacenado.getNombre() );
        documentoAlmacenadoDto.setFileName( documentoAlmacenado.getFileName() );
        documentoAlmacenadoDto.setExtension( documentoAlmacenado.getExtension() );
        documentoAlmacenadoDto.setEstado( documentoAlmacenado.getEstado() );
        documentoAlmacenadoDto.setEliminado( documentoAlmacenado.isEliminado() );
        documentoAlmacenadoDto.setFile( documentoAlmacenado.getFile() );
        documentoAlmacenadoDto.setUrlFile( documentoAlmacenado.getUrlFile() );

        return documentoAlmacenadoDto;
    }

    @Override
    public DocumentoAlmacenado toEntity(DocumentoAlmacenadoDto documentoAlmacenadoDto) {
        if ( documentoAlmacenadoDto == null ) {
            return null;
        }

        DocumentoAlmacenado documentoAlmacenado = new DocumentoAlmacenado();

        documentoAlmacenado.setId( documentoAlmacenadoDto.getId() );
        documentoAlmacenado.setNombre( documentoAlmacenadoDto.getNombre() );
        documentoAlmacenado.setFileName( documentoAlmacenadoDto.getFileName() );
        documentoAlmacenado.setExtension( documentoAlmacenadoDto.getExtension() );
        documentoAlmacenado.setEstado( documentoAlmacenadoDto.getEstado() );
        documentoAlmacenado.setEliminado( documentoAlmacenadoDto.isEliminado() );
        documentoAlmacenado.setFile( documentoAlmacenadoDto.getFile() );
        documentoAlmacenado.setUrlFile( documentoAlmacenadoDto.getUrlFile() );

        return documentoAlmacenado;
    }
}
