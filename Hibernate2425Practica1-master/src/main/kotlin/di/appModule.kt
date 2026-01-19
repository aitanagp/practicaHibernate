package ies.sequeros.dam.ad.orm.di

import ies.sequeros.dam.ad.orm.application.categorias.*
import ies.sequeros.dam.ad.orm.application.productos.*
import ies.sequeros.dam.ad.orm.domain.ICategoriaRepository
import ies.sequeros.dam.ad.orm.domain.IProductoRepository
import ies.sequeros.dam.ad.orm.infraestructure.repositories.JPACategoriaRepository
import ies.sequeros.dam.ad.orm.infraestructure.repositories.JPAProductoRepository
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.koin.dsl.module
import org.koin.dsl.onClose

val appModulo = module {

    // Configuración de la base de datos
    single<EntityManagerFactory> {
        Persistence.createEntityManagerFactory("UnidadPersistencia")
    }.onClose {
        it?.close() // Se cierra la factoría al terminar [cite: 388, 391]
    }

    // --- REPOSITORIOS ---
    // El sistema infiere que necesitan el EntityManagerFactory y lo inyecta [cite: 393, 395]
    single<ICategoriaRepository> { JPACategoriaRepository(get()) }
    single<IProductoRepository> { JPAProductoRepository(get()) }

    // --- CASOS DE USO DE CATEGORÍAS ---
    factory { GetCategoriaUseCase(get()) }
    factory { AddCategoriaUseCase(get()) }
    factory { UpdateCategoriaUseCase(get()) }
    factory { DeleteCategoriaUseCase(get()) }
    factory { GetAllCategoriaUseCase(get()) }

    // --- CASOS DE USO DE PRODUCTOS ---
    // Se usa 'factory' para crear una instancia nueva cada vez que se inyectan
    factory { AddProductoUseCase(get()) }
    factory { GetProductoUseCase(get()) }
    factory { GetAllProductoUseCase(get()) }
    factory { UpdateProductoUseCase(get()) }
    factory { DeleteProductoUseCase(get()) }
}