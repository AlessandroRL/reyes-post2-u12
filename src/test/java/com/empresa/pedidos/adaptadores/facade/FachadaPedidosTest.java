package com.empresa.pedidos.adaptadores.facade;

import com.empresa.pedidos.aplicacion.ServicioPedidos;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FachadaPedidosTest {

    @Mock
    private ServicioPedidos servicioPedidos;

    @InjectMocks
    private FachadaPedidos fachadaPedidos;

    @Test
    void delegaCreacionAlServicio() {
        Pedido pedido = new Pedido(null, TipoPedido.ESTANDAR, new BigDecimal("100.00"));
        when(servicioPedidos.crearPedido(pedido)).thenReturn(pedido);

        Pedido resultado = fachadaPedidos.crearPedido(pedido);

        assertThat(resultado).isSameAs(pedido);
        verify(servicioPedidos).crearPedido(pedido);
    }

    @Test
    void delegaBusquedaAlServicio() {
        Pedido pedido = new Pedido(1L, TipoPedido.ESTANDAR, new BigDecimal("100.00"));
        when(servicioPedidos.buscarPorId(1L)).thenReturn(Optional.of(pedido));

        Optional<Pedido> resultado = fachadaPedidos.buscarPorId(1L);

        assertThat(resultado).containsSame(pedido);
        verify(servicioPedidos).buscarPorId(1L);
    }
}
