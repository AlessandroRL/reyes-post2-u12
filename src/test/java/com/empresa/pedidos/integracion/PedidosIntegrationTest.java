package com.empresa.pedidos.integracion;

import com.empresa.pedidos.adaptadores.facade.FachadaPedidos;
import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.infraestructura.notificaciones.NotificacionEmail;
import com.empresa.pedidos.infraestructura.notificaciones.NotificacionLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PedidosIntegrationTest {

    @Autowired
    private FachadaPedidos fachadaPedidos;

    @SpyBean
    private NotificacionEmail notificacionEmail;

    @SpyBean
    private NotificacionLog notificacionLog;

    @Test
    void creaPedidoPersisteProcesaYPublicaEvento() {
        Pedido pedido = new Pedido(null, TipoPedido.EXPRESS, new BigDecimal("100.00"));

        Pedido creado = fachadaPedidos.crearPedido(pedido);

        assertThat(creado.getId()).isNotNull();
        assertThat(creado.getEstado()).isEqualTo(EstadoPedido.PROCESADO);
        assertThat(creado.getCosto()).isEqualByComparingTo("130.00");
        verify(notificacionEmail).notificar(any());
        verify(notificacionLog).notificar(any());
    }
}
