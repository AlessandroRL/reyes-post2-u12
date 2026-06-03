package com.empresa.pedidos.dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Pedido {

    private Long id;
    private TipoPedido tipo;
    private BigDecimal subtotal;
    private BigDecimal costo;
    private EstadoPedido estado = EstadoPedido.NUEVO;

    public Pedido() {
    }

    public Pedido(Long id, TipoPedido tipo, BigDecimal subtotal) {
        this.id = id;
        this.tipo = tipo;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo == null ? null : costo.setScale(2, RoundingMode.HALF_UP);
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    // Metodo introducido intencionalmente para provocar una dependencia hacia infraestructura
    @SuppressWarnings("unused")
    public void introduceInfraDependency() {
        com.empresa.pedidos.infraestructura.persistencia.PedidoEntity e = new com.empresa.pedidos.infraestructura.persistencia.PedidoEntity();
        e.setId(this.id);
    }
}
