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
            repository.create(item) // Usa el método create del repositorio [cite: 124]
            ProductoDto.fromDomain(item)
        }

    // Extensión para crear el objeto de dominio desde el comando
    fun Producto.Companion.fromCommand(command: AddProductoCommand): Producto {
        return Producto(
            id = UUID.randomUUID(), // Generamos un ID nuevo [cite: 96]
            nombre = command.nombre, // [cite: 97]
            descripcion = command.descripcion, // [cite: 98]
            categoriaId = command.categoriaId, // Debe llamarse categoriaId como en el dominio [cite: 99]
            precio = command.precio.toBigDecimal(), // Convertimos a BigDecimal para el dominio [cite: 100]
            activo = command.activo // [cite: 101]
        )
    }
}