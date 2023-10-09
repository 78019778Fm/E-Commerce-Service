package com.alexandertutoriales.ecommerce.service.controller;

import com.alexandertutoriales.ecommerce.service.entity.DocumentoAlmacenado;
import com.alexandertutoriales.ecommerce.service.service.DocumentoAlmacenadoService;
import com.alexandertutoriales.ecommerce.service.utlis.GenericResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/documento-almacenado")
public class DocumentoAlmacenadoController {
    private DocumentoAlmacenadoService service;

    public DocumentoAlmacenadoController(DocumentoAlmacenadoService service) {
        this.service = service;
    }

    @GetMapping
    public GenericResponse list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public GenericResponse find(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        return service.downloadByFileName(fileName, request);
    }

    @PostMapping
    public GenericResponse save(@ModelAttribute DocumentoAlmacenado obj) {
        return service.save(obj);
    }

    public GenericResponse delete (Long aLong, DocumentoAlmacenado obj) {
        return null;
    }
    @PutMapping("/editImage/{id}")
    public GenericResponse update(@PathVariable Long id, @ModelAttribute DocumentoAlmacenado obj) {
        obj.setId(id);
        return service.save(obj);
    }

    @DeleteMapping("/deleteImage/{id}")
    public GenericResponse delete(@PathVariable Long id) {
        return service.deleteById(id);
    }
}
