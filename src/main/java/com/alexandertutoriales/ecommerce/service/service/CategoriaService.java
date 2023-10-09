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
        Page<Categoria> listaOk = setCategoriaString(lista);
        return mapper.toDto(listaOk);
    }

    private Page<Categoria> setCategoriaString(Page<Categoria> listaCategoria) {
        if (listaCategoria != null) {
            for (Categoria listaCate : listaCategoria) {
                listaCate.setVigenciaString(listaCate.isVigencia() ? "Activo" : "Inactivo");
            }
        }
        return listaCategoria;
    }

    public Integer save(CategoriaDto categoriaDto) {
        Categoria categoria = mapper.toEntity(categoriaDto);
        Categoria categoriaGuardada = repository.save(categoria);
        return categoriaGuardada.getId();
    }

    public CategoriaDto findCategoriaById(int id) {
        return mapper.toDto(repository.findById(id));
    }

    public void deleteCategoriaById(int id){
        repository.deleteById(id);
    }

    public void activar(Integer id) {
        repository.activar(id);
    }

    public void desactivar(Integer id) {
        repository.desactivar(id);
    }
}
