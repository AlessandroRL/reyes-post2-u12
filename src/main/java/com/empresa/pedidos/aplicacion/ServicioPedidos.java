package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioPedidos {

    private final ProcesadorPedidoFactory factory;
    private final RepositorioPedidos repositorio;
    private final ApplicationEventPublisher publisher;

    public ServicioPedidos(ProcesadorPedidoFactory factory,
                           RepositorioPedidos repositorio,
                           ApplicationEventPublisher publisher) {
        this.factory = factory;
        this.repositorio = repositorio;
        this.publisher = publisher;
    }

    public Pedido crearPedido(Pedido pedido) {
        ProcesadorPedido procesador = factory.obtener(pedido.getTipo());
        procesador.procesar(pedido);
        Pedido guardado = repositorio.guardar(pedido);
        publisher.publishEvent(new PedidoProcesadoEvent(guardado));
        return guardado;
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }
}
