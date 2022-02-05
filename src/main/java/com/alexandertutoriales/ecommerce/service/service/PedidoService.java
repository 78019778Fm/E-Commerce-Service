package com.alexandertutoriales.ecommerce.service.service;

import com.alexandertutoriales.ecommerce.service.entity.DetallePedido;
import com.alexandertutoriales.ecommerce.service.entity.Pedido;
import com.alexandertutoriales.ecommerce.service.entity.dto.GenerarPedidoDTO;
import com.alexandertutoriales.ecommerce.service.entity.dto.PedidoConDetallesDTO;
import com.alexandertutoriales.ecommerce.service.repository.DetallePedidoRepository;
import com.alexandertutoriales.ecommerce.service.repository.PedidoRepository;
import com.alexandertutoriales.ecommerce.service.repository.PlatilloRepository;
import com.alexandertutoriales.ecommerce.service.utlis.GenericResponse;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.util.ResourceUtils;

import static com.alexandertutoriales.ecommerce.service.utlis.Global.*;

@Service
@Transactional
public class PedidoService {

  private final PedidoRepository repository;

  private final DetallePedidoRepository detallePedidoRepository;

  private final DetallePedidoService dpService;

  private final PlatilloRepository pRepository;

  public PedidoService(PedidoRepository repository, DetallePedidoRepository detallePedidoRepository, DetallePedidoService dpService,
      PlatilloRepository pRepository) {
    this.repository = repository;
    this.detallePedidoRepository = detallePedidoRepository;
    this.dpService = dpService;
    this.pRepository = pRepository;
  }

  //Método para devolver los pedidos con su respectivo detalle
  public GenericResponse<List<PedidoConDetallesDTO>> devolverMisCompras(int idCli) {
    final List<PedidoConDetallesDTO> dtos = new ArrayList<>();
    final Iterable<Pedido> pedidos = repository.devolverMisCompras(idCli);
    pedidos.forEach(p -> {
      dtos.add(new PedidoConDetallesDTO(p, detallePedidoRepository.findByPedido(p.getId())));
    });
    return new GenericResponse(OPERACION_CORRECTA, RPTA_OK, "Petición Encontrada", dtos);
  }

  //Método para guardar el pedido
  public GenericResponse guardarPedido(GenerarPedidoDTO dto) {
    Date date = new Date();
    dto.getPedido().setFechaCompra(new java.sql.Date(date.getTime()));
    dto.getPedido().setAnularPedido(false);
    dto.getPedido().setMonto(dto.getPedido().getMonto());
    dto.getPedido().setCliente(dto.getCliente());
    this.repository.save(dto.getPedido());
    for (DetallePedido dp : dto.getDetallePedidos()) {
      dp.setPedido(dto.getPedido());
      this.pRepository.descontarStock(dp.getCantidad(), dp.getPlatillo().getId());
    }
    //Llamamos al DetallePedidoService
    this.dpService.guardarDetalles(dto.getDetallePedidos());
    return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, dto);

  }

  //Método para anular el pedido
  public GenericResponse anularPedido(int id) {
    Pedido p = this.repository.findById(id).orElse(new Pedido());
    if (p.getId() != 0) {
      p.setAnularPedido(true);
      this.restablecerStock(id);
      this.repository.save(p);
      return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_CORRECTA, p);
    } else {
      return new GenericResponse(TIPO_DATA, RPTA_OK, OPERACION_ERRONEA, "El pedido que desea anular no es válido");
    }
  }

  private void restablecerStock(final int pedidoId) {
    Iterable<DetallePedido> detalles = this.detallePedidoRepository.findByPedido(pedidoId);
    for (DetallePedido dp : detalles) {
      pRepository.aumentarStock(dp.getCantidad(), dp.getPlatillo().getId());
    }
  }

  @NotNull
  public ResponseEntity<Resource> exportInvoice(int idCli, int idOrden) {
    Optional<Pedido> optPedido = this.repository.findByIdAndClienteId(idCli, idOrden);
    Double rpta = this.detallePedidoRepository.totalByIdCustomer(idCli);
    if (optPedido.isPresent()) {
      try {
        final Pedido pedido = optPedido.get();
        final File file = ResourceUtils.getFile("classpath:exportInvoice.jasper");
        final File imgLogo = ResourceUtils.getFile("classpath:images/logoCevicheria.png");
        final JasperReport report = (JasperReport) JRLoader.loadObject(file);

        final HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("nombreCliente", pedido.getCliente().getNombreCompletoCiente());
        parameters.put("imgLogo", new FileInputStream(imgLogo));
        parameters.put("total", rpta);
        parameters.put("dsInvoice", new JRBeanCollectionDataSource((Collection<?>) this.detallePedidoRepository.findByPedido(idOrden)));

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
        String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
        StringBuilder stringBuilder = new StringBuilder().append("InvoicePDF:");
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
            .filename(stringBuilder.append(pedido.getId())
                .append("generateDate:")
                .append(sdf)
                .append(".pdf")
                .toString())
            .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().contentLength((long) reporte.length)
            .contentType(MediaType.APPLICATION_PDF)
            .headers(headers).body(new ByteArrayResource(reporte));
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      return ResponseEntity.noContent().build(); //No se encontro el reporte
    }
    return null;
  }
}
