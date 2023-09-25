package com.alexandertutoriales.ecommerce.service.controller;

import com.alexandertutoriales.ecommerce.service.entity.dto.CategoriaDto;
import com.alexandertutoriales.ecommerce.service.entity.filters.CategoriaFilter;
import com.alexandertutoriales.ecommerce.service.exception.UnprocessableEntityException;
import com.alexandertutoriales.ecommerce.service.service.CategoriaService;
import com.alexandertutoriales.ecommerce.service.utlis.GenericResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public GenericResponse listarCategoriasActivas() {
        return this.service.listarCategoriasActivas();
    }

    @PostMapping("/filtrar")
    public ResponseEntity<Page<CategoriaDto>> filtrar(Pageable pageRequest, @Valid
    @RequestBody(required = false) CategoriaFilter filter) {
        return ResponseEntity.ok(this.service.findAll(pageRequest, filter));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable(required = true) int id) {
        CategoriaDto categoriaDto = this.service.findCategoriaById(id);
        return ResponseEntity.ok(categoriaDto);
    }

    @PostMapping
    public ResponseEntity<Integer> create(@Valid @RequestBody CategoriaDto categoriaDto) {
        if(categoriaDto.getId() != null) {
            throw new UnprocessableEntityException();
        }
        Integer id = this.service.save(categoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);

    }
}
