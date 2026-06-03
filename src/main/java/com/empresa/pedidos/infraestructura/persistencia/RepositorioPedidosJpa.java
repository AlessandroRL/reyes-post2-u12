package com.empresa.pedidos.infraestructura.persistencia;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class RepositorioPedidosJpa implements RepositorioPedidos {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pedido guardar(Pedido pedido) {
        PedidoEntity entity = mapearAEntidad(pedido);
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        entityManager.flush();
        return mapearADominio(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager.find(PedidoEntity.class, id))
                .map(this::mapearADominio);
    }

    private PedidoEntity mapearAEntidad(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());
        entity.setTipo(pedido.getTipo());
        entity.setSubtotal(pedido.getSubtotal());
        entity.setCosto(pedido.getCosto());
        entity.setEstado(pedido.getEstado());
        return entity;
    }

    private Pedido mapearADominio(PedidoEntity entity) {
        Pedido pedido = new Pedido();
        pedido.setId(entity.getId());
        pedido.setTipo(entity.getTipo());
        pedido.setSubtotal(entity.getSubtotal());
        pedido.setCosto(entity.getCosto());
        pedido.setEstado(entity.getEstado());
        return pedido;
    }
}
