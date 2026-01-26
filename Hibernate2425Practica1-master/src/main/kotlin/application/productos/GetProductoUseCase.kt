package ies.sequeros.dam.ad.orm.application.productos

import ies.sequeros.dam.ad.orm.application.productos.dtos.ProductoDto
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class GetProductoUseCase(private val repository: IProductoRepository) {
    suspend operator fun invoke(id: UUID): ProductoDto? =
        withContext(Dispatchers.IO) {
            repository.findById(id)?.let { ProductoDto.fromDomain(it) }
        }
}