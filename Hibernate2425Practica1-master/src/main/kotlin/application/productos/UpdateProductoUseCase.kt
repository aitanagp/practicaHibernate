package ies.sequeros.dam.ad.orm.application.productos

import ies.sequeros.dam.ad.orm.application.productos.commands.UpdateProductoCommand
import ies.sequeros.dam.ad.orm.application.productos.dtos.ProductoDto
import ies.sequeros.dam.ad.orm.domain.Producto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateProductoUseCase(private val repository: IProductoRepository) {
    suspend operator fun invoke(command: UpdateProductoCommand): ProductoDto =
        withContext(Dispatchers.IO) {
            val item = Producto.fromCommand(command)
            repository.update(item) // Usa el método update del repositorio [cite: 123]
            ProductoDto.fromDomain(item)
        }

    fun Producto.Companion.fromCommand(command: UpdateProductoCommand): Producto {
        return Producto(
            id = command.id, // Usamos el ID existente para actualizar [cite: 96]
            nombre = command.nombre,
            descripcion = command.descripcion,
            categoriaId = command.categoriaId,
            precio = command.precio.toBigDecimal(),
            activo = command.activo
        )
    }
}