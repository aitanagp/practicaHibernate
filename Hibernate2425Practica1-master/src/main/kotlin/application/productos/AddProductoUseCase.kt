package ies.sequeros.dam.ad.orm.application.productos

import ies.sequeros.dam.ad.orm.application.productos.commands.AddProductoCommand
import ies.sequeros.dam.ad.orm.application.productos.dtos.ProductoDto
import ies.sequeros.dam.ad.orm.domain.Producto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class AddProductoUseCase(private val repository: IProductoRepository) {
    suspend operator fun invoke(command: AddProductoCommand): ProductoDto =
        withContext(Dispatchers.IO) {
            val item = Producto.fromCommand(command)
            repository.create(item)
            ProductoDto.fromDomain(item)
        }

    fun Producto.Companion.fromCommand(command: AddProductoCommand): Producto {
        return Producto(
            id = UUID.randomUUID(),
            nombre = command.nombre,
            descripcion = command.descripcion,
            categoriaId = command.categoriaId,
            precio = command.precio.toBigDecimal(),
            activo = command.activo
        )
    }
}