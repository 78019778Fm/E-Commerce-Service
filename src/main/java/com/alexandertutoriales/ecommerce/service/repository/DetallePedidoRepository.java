package com.alexandertutoriales.ecommerce.service.repository;

import com.alexandertutoriales.ecommerce.service.entity.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DetallePedidoRepository extends CrudRepository<DetallePedido, Integer> {
    @Query("SELECT DP FROM DetallePedido DP WHERE DP.pedido.id=:idP")
    Iterable<DetallePedido> findByPedido(int idP);
    @Query(value = "SELECT SUM(dp.cantidad * dp.precio) AS \"Total\" FROM detalle_pedido dp JOIN pedido p\n"
        + "ON p.id = dp.pedido_id\n"
        + "WHERE p.cliente_id =:idCli", nativeQuery = true)
    Double totalByIdCustomer(int idCli);

}
