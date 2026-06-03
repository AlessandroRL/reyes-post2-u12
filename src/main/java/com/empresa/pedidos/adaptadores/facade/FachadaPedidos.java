package com.empresa.pedidos.adaptadores.facade;

import com.empresa.pedidos.aplicacion.ServicioPedidos;
import com.empresa.pedidos.dominio.Pedido;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FachadaPedidos {

    private final ServicioPedidos servicioPedidos;

    public FachadaPedidos(ServicioPedidos servicioPedidos) {
        this.servicioPedidos = servicioPedidos;
    }

    public Pedido crearPedido(Pedido pedido) {
        return servicioPedidos.crearPedido(pedido);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return servicioPedidos.buscarPorId(id);
    }
}
