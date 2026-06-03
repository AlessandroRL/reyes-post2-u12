package com.empresa.pedidos.infraestructura.notificaciones;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.TipoPedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NotificacionPedidoProcesadoTest {

    @Test
    void ejecutaNotificacionesSinError() {
        Pedido pedido = new Pedido(10L, TipoPedido.EXPRESS, new BigDecimal("100.00"));
        pedido.setCosto(new BigDecimal("130.00"));
        pedido.setEstado(EstadoPedido.PROCESADO);
        PedidoProcesadoEvent evento = new PedidoProcesadoEvent(pedido);

        new NotificacionEmail().notificar(evento);
        new NotificacionLog().notificar(evento);

        assertThat(evento.pedido().getEstado()).isEqualTo(EstadoPedido.PROCESADO);
    }
}
