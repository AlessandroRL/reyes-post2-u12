package com.empresa.pedidos.adaptadores.procesadores;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class ProcesadorPedidoExpress implements ProcesadorPedido {

    private static final BigDecimal MULTIPLICADOR = new BigDecimal("1.30");

    @Override
    public TipoPedido getTipo() {
        return TipoPedido.EXPRESS;
    }

    @Override
    public void procesar(Pedido pedido) {
        Objects.requireNonNull(pedido.getSubtotal(), "subtotal no puede ser nulo");
        pedido.setCosto(pedido.getSubtotal().multiply(MULTIPLICADOR));
        pedido.setEstado(EstadoPedido.PROCESADO);
    }
}
