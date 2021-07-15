package com.alexandertutoriales.ecommerce.service.service;

import com.alexandertutoriales.ecommerce.service.entity.Cliente;
import com.alexandertutoriales.ecommerce.service.repository.ClienteRepository;
import com.alexandertutoriales.ecommerce.service.utlis.GenericResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.alexandertutoriales.ecommerce.service.utlis.Global.*;

@Service
@Transactional
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    //Método para guardar y actualizar cliente
    public GenericResponse save(Cliente c){
        Optional<Cliente> opt = this.repository.findById(c.getId());
        int idf = opt.isPresent() ? opt.get().getId() : 0;
        if(idf == 0){
            if(repository.existByDoc(c.getNumDoc().trim()) == 1){
                return new GenericResponse(TIPO_RESULT, RPTA_WARNING, "Lo sentimos: " +
                        "Ya existe un cliente con ese mismo numero de documento, " +
                        "y si el problema persiste comuniquese con el soporte técnico", null);
            }else{
                //Guarda
                c.setId(idf);
                return new GenericResponse(TIPO_DATA, RPTA_OK, "Cliente registrado correctamente", this.repository.save(c));
            }
        }else{
            //Actualizar Registro
            if(repository.existByDocForUpdate(c.getNumDoc().trim(), c.getId()) == 1){
                return new GenericResponse(TIPO_RESULT, RPTA_WARNING, "Error: Ya existe un cliente con esos mismos datos" +
                        "verifique e intente de nuevo", null);
            }else{
                //Actualiza
                c.setId(idf);
                return new GenericResponse(TIPO_DATA, RPTA_OK, "Datos del cliente actualizado", this.repository.save(c));
            }
        }
    }
}
