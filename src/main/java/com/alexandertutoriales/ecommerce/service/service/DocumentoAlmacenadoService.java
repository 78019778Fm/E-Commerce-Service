package com.alexandertutoriales.ecommerce.service.service;

import com.alexandertutoriales.ecommerce.service.entity.DocumentoAlmacenado;
import com.alexandertutoriales.ecommerce.service.repository.DocumentoAlmacenadoRepository;
import com.alexandertutoriales.ecommerce.service.utlis.GenericResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import static com.alexandertutoriales.ecommerce.service.utlis.Global.*;


@Service
@Transactional
public class DocumentoAlmacenadoService {
    private DocumentoAlmacenadoRepository repo;
    private FileStorageService storageService;

    public DocumentoAlmacenadoService(DocumentoAlmacenadoRepository repo, FileStorageService storageService) {
        this.repo = repo;
        this.storageService = storageService;
    }

    public GenericResponse<Iterable<DocumentoAlmacenado>> list() {
        return new GenericResponse<Iterable<DocumentoAlmacenado>>(TIPO_RESULT, RPTA_OK, OPERACION_CORRECTA, repo.list());
    }


    public GenericResponse find(Long aLong) {
        return null;
    }


    public GenericResponse save(DocumentoAlmacenado obj) {
        String fileName = (repo.findById(obj.getId())).orElse(new DocumentoAlmacenado()).getFileName();

        String originalFilename = obj.getFile().getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        fileName = storageService.storeFile(obj.getFile(), fileName);

        obj.setFileName(fileName);
        obj.setExtension(extension);

        return new GenericResponse(TIPO_DATA, RPTA_OK,OPERACION_CORRECTA,repo.save(obj));
    }

    public ResponseEntity<Resource> download(String completefileName, HttpServletRequest request) {
        Resource resource = storageService.loadResource(completefileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> downloadByFileName(String fileName, HttpServletRequest request) {
        DocumentoAlmacenado doc = repo.findByFileName(fileName).orElse(new DocumentoAlmacenado());
        return download(doc.getCompleteFileName(), request);
    }

    public HashMap<String, Object> validate(DocumentoAlmacenado obj) {
        return null;
    }

    public GenericResponse deleteById(Long id) {
        boolean deleted = false;
        Optional<DocumentoAlmacenado> documentoAlmacenado = repo.findById(id);
        if (documentoAlmacenado.isPresent()) {
            deleted = storageService.deleteFile(documentoAlmacenado.get().getCompleteFileName());
            int deletedFromBD = repo.deleteImageById(documentoAlmacenado.get().getId());
            if (deletedFromBD == 1 && deleted) {
                return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, deleted);
            } else {
                return new GenericResponse(TIPO_DATA, RPTA_WARNING, OPERACION_ERRONEA, deleted);
            }
        } else {
            return new GenericResponse(TIPO_DATA, RPTA_ERROR, "No se ha encontrado ningún documento almacenado con ese Id", deleted);
        }
    }
}
