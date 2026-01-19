package ies.sequeros.dam.ad.orm.application.productos.commands

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UpdateProductoCommand(
    val id: UUID,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val categoriaId: UUID,
    val activo: Boolean
)