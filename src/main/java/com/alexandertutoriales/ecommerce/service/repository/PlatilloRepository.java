package com.alexandertutoriales.ecommerce.service.repository;

import com.alexandertutoriales.ecommerce.service.entity.Platillo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlatilloRepository extends CrudRepository<Platillo, Integer> {
    @Query("SELECT P FROM Platillo P WHERE P.recomendado IS 1")
    Iterable<Platillo> listarPlatillosRecomendados();

    @Query("SELECT P FROM Platillo P WHERE P.categoria.id=:idC")
    Iterable<Platillo> listarPlatillosPorCategoria(int idC);

    @Modifying
    @Query("UPDATE Platillo P SET P.stock=stock-:cant WHERE P.id=:id")
    void descontarStock(int cant, int id);

    @Modifying
    @Query("UPDATE Platillo P SET P.stock=stock+:cant WHERE P.id=:id")
    void aumentarStock(int cant, int id);
}
