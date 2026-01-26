package ies.sequeros.dam.ad.orm.application.productos

import ies.sequeros.dam.ad.orm.application.productos.commands.*
import ies.sequeros.dam.ad.orm.application.productos.dtos.ProductoDto
import ies.sequeros.dam.ad.orm.domain.Producto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class DeleteProductoUseCase(private val repository: IProductoRepository) {
    suspend operator fun invoke(id: UUID): Boolean =
        withContext(Dispatchers.IO) {
            val exists = repository.existsById(id)
            if (exists) {
                repository.delete(id)
            }
            exists
        }
}