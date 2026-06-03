package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProcesadorPedidoFactory {

    private final Map<TipoPedido, ProcesadorPedido> procesadores;

    public ProcesadorPedidoFactory(List<ProcesadorPedido> listaProcesadores) {
        this.procesadores = listaProcesadores.stream().collect(
                Collectors.toMap(
                        ProcesadorPedido::getTipo,
                        Function.identity(),
                        (existente, duplicado) -> existente,
                        () -> new EnumMap<>(TipoPedido.class)
                )
        );
    }

    public ProcesadorPedido obtener(TipoPedido tipo) {
        Objects.requireNonNull(tipo, "tipo no puede ser nulo");
        return Optional.ofNullable(procesadores.get(tipo))
                .orElseThrow(() -> new IllegalArgumentException("Tipo de pedido no soportado: " + tipo));
    }
}
