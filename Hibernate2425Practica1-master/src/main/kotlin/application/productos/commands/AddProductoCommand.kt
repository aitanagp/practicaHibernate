package ies.sequeros.dam.ad.orm.application.productos.commands

import ies.sequeros.dam.ad.orm.application.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class AddProductoCommand(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    @Serializable(with = UUIDSerializer::class)
    val categoriaId: UUID,
    val activo: Boolean = false
)