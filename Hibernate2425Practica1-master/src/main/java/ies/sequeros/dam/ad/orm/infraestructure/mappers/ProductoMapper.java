package ies.sequeros.dam.ad.orm.infraestructure.mappers;

import ies.sequeros.dam.ad.orm.domain.Producto;
import ies.sequeros.dam.ad.orm.infraestructure.entities.ProductoJPA;

import java.math.BigDecimal;
import java.security.cert.Extension;
import java.util.UUID;

public class ProductoMapper {
    private ProductoMapper() {}

    public static Producto toDomain(ProductoJPA jpa) {
        if (jpa == null) return null;

        Double precioDouble = (jpa.getPrecio() != null) ? jpa.getPrecio().doubleValue() : 0.0;

        UUID id = jpa.getCategoria() != null ? jpa.getCategoria().getId() : null;
        return new Producto(
                jpa.getId(),
                jpa.getNombre(),
                jpa.getDescripcion(),
                id,
                jpa.getPrecio(),
                jpa.getActivo() != null ? jpa.getActivo() : false
        );
    }

    public static ProductoJPA toJpa(Producto domain) {
        if (domain == null) return null;

        ProductoJPA jpa = new ProductoJPA();
        jpa.setId(domain.getId() != null ? domain.getId() : UUID.randomUUID());

        jpa.setNombre(domain.getNombre());
        jpa.setDescripcion(domain.getDescripcion());
        jpa.setActivo(domain.getActivo());

        if (domain.getPrecio() != null) {
            jpa.setPrecio(domain.getPrecio());
        } else {
            jpa.setPrecio(BigDecimal.ZERO);
        }
        return jpa;
    }
}
