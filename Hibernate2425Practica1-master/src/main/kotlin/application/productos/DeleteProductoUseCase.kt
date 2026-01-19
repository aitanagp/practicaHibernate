package ies.sequeros.dam.ad.orm.application.productos

// Importas los comandos, dtos y clases de dominio necesarias
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
            val exists = repository.existsById(id) // [cite: 128]
            if (exists) {
                repository.delete(id) // [cite: 126]
            }
            exists
        }
}