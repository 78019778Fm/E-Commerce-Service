package com.alexandertutoriales.ecommerce.service.service;

import com.alexandertutoriales.ecommerce.service.entity.Categoria;
import com.alexandertutoriales.ecommerce.service.entity.dto.CategoriaDto;
import com.alexandertutoriales.ecommerce.service.entity.filters.CategoriaFilter;
import com.alexandertutoriales.ecommerce.service.mapper.CategoriaMapper;
import com.alexandertutoriales.ecommerce.service.repository.CategoriaRepository;
import com.alexandertutoriales.ecommerce.service.spec.CategoriaSpec;
import com.alexandertutoriales.ecommerce.service.utlis.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Order;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.alexandertutoriales.ecommerce.service.utlis.Global.*;

@Service
@Transactional
public class CategoriaService {
    private final CategoriaRepository repository;
    private final CategoriaSpec spec;
    private final CategoriaMapper mapper;

    public CategoriaService(CategoriaRepository repository, CategoriaSpec spec, CategoriaMapper mapper) {
        this.repository = repository;
        this.spec = spec;
        this.mapper = mapper;
    }

    public GenericResponse listarCategoriasActivas() {
        return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, this.repository.listarCategoriasActivas());
    }

    public Page<CategoriaDto> findAll(Pageable page, CategoriaFilter filter) {
        // Comprobamos si existe orden, sino ponenemos el generico.
        if (page.getSort() == null) {
            List<Order> listaOrden = new ArrayList<>();
            listaOrden.add(new Order(Sort.Direction.ASC, Categoria.C_NOMBRE));
            Sort sort = Sort.by(listaOrden);
            page = PageRequest.of(page.getPageNumber(), page.getPageSize(), sort);
        }
        Page<Categoria> lista = repository.findAll(spec.filtrar(filter), page);
        return mapper.toDto(lista);
    }
}
