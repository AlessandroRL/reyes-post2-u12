package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoEstandar;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoExpress;
import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoInternacional;
import com.empresa.pedidos.dominio.TipoPedido;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProcesadorPedidoFactoryTest {

    private final ProcesadorPedidoFactory factory = new ProcesadorPedidoFactory(List.of(
            new ProcesadorPedidoEstandar(),
            new ProcesadorPedidoExpress(),
            new ProcesadorPedidoInternacional()
    ));

    @Test
    void retornaProcesadorEstandar() {
        assertThat(factory.obtener(TipoPedido.ESTANDAR))
                .isInstanceOf(ProcesadorPedidoEstandar.class);
    }

    @Test
    void retornaProcesadorExpress() {
        assertThat(factory.obtener(TipoPedido.EXPRESS))
                .isInstanceOf(ProcesadorPedidoExpress.class);
    }

    @Test
    void retornaProcesadorInternacional() {
        assertThat(factory.obtener(TipoPedido.INTERNACIONAL))
                .isInstanceOf(ProcesadorPedidoInternacional.class);
    }

    @Test
    void fallaSiElTipoEsNulo() {
        assertThatThrownBy(() -> factory.obtener(null))
                .isInstanceOf(NullPointerException.class);
    }
}
