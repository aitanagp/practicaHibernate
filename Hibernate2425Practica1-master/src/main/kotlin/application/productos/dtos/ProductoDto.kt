@file:UseContextualSerialization(UUID::class)

package ies.sequeros.dam.ad.orm.application.productos.dtos

import ies.sequeros.dam.ad.orm.domain.Categoria
import ies.sequeros.dam.ad.orm.domain.Producto
import jakarta.persistence.Id
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.gradle.internal.impldep.com.esotericsoftware.kryo.serializers.DefaultSerializers.BigDecimalSerializer
import java.math.BigDecimal
import java.util.UUID


@Serializable
data class ProductoDto(val id: UUID,
                       val nombre:String,
                       val descripcion:String,
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
