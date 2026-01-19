package ies.sequeros.dam.ad.orm.application.productos.dtos

import ies.sequeros.dam.ad.orm.application.serializers.UUIDSerializer
import ies.sequeros.dam.ad.orm.application.serializers.BigDecimalSerializer
import ies.sequeros.dam.ad.orm.domain.Producto
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.util.UUID


@Serializable
data class ProductoDto(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val nombre:String,
    val descripcion:String,
    @Serializable(with = UUIDSerializer::class)
    val categoria: UUID,
    @Serializable(with = BigDecimalSerializer::class)
    val precio:BigDecimal,
    val activo:Boolean
    ) {
    companion object{
        fun fromDomain(item: Producto): ProductoDto {
            return ProductoDto(id = item.id!!,
                nombre = item.nombre,
                descripcion = item.descripcion,
                categoria = item.categoriaId,
                precio = item.precio,
                activo = item.activo)
        }
    }
}
