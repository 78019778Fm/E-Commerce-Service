package com.alexandertutoriales.ecommerce.service.spec;

import com.alexandertutoriales.ecommerce.service.entity.Categoria;
import com.alexandertutoriales.ecommerce.service.entity.filters.CategoriaFilter;
import org.springframework.data.jpa.domain.Specification;

public interface CategoriaSpec {
    Specification<Categoria> filtrar (CategoriaFilter filter);
}
