package ies.sequeros.dam.ad.orm.infraestructure.mappers;

import ies.sequeros.dam.ad.orm.domain.Producto;
import ies.sequeros.dam.ad.orm.infraestructure.entities.ProductoJPA;

import java.security.cert.Extension;

public class ProductoMapper {
    private ProductoMapper() {}

    public static Producto toDomain(ProductoJPA jpa) {
        return new Producto(
                jpa.getId(),
                jpa.getNombre(),
                jpa.getDescripcion(),
                jpa.getCategoria().getId(),
                jpa.getPrecio(),
                jpa.getActivo()
        );
    }

    public static ProductoJPA toJpa(Producto domain) {
        ProductoJPA jpa = new ProductoJPA();
        jpa.setId(domain.getId());
        jpa.setNombre(domain.getNombre());
        jpa.setDescripcion(domain.getDescripcion());
        jpa.setActivo(domain.getActivo());
        return jpa;
    }
}
