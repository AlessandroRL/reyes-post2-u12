package com.empresa.pedidos.adaptadores.procesadores;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProcesadorPedidosTest {

    @Test
    void procesaPedidoEstandar() {
        Pedido pedido = new Pedido(null, TipoPedido.ESTANDAR, new BigDecimal("100.00"));

        new ProcesadorPedidoEstandar().procesar(pedido);

        assertThat(pedido.getCosto()).isEqualByComparingTo("110.00");
        assertThat(pedido.getEstado()).isEqualTo(EstadoPedido.PROCESADO);
    }

    @Test
    void procesaPedidoExpress() {
        Pedido pedido = new Pedido(null, TipoPedido.EXPRESS, new BigDecimal("100.00"));

        new ProcesadorPedidoExpress().procesar(pedido);

        assertThat(pedido.getCosto()).isEqualByComparingTo("130.00");
        assertThat(pedido.getEstado()).isEqualTo(EstadoPedido.PROCESADO);
    }

    @Test
    void procesaPedidoInternacional() {
        Pedido pedido = new Pedido(null, TipoPedido.INTERNACIONAL, new BigDecimal("100.00"));

        new ProcesadorPedidoInternacional().procesar(pedido);

        assertThat(pedido.getCosto()).isEqualByComparingTo("175.00");
        assertThat(pedido.getEstado()).isEqualTo(EstadoPedido.PROCESADO);
    }
}
