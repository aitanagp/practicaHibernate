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

    single<EntityManagerFactory> {
        Persistence.createEntityManagerFactory("UnidadPersistencia")
    }.onClose {
        it?.close()
    }

    single<ICategoriaRepository> { JPACategoriaRepository(get()) }
    single<IProductoRepository> { JPAProductoRepository(get()) }

    factory { GetCategoriaUseCase(get()) }
    factory { AddCategoriaUseCase(get()) }
    factory { UpdateCategoriaUseCase(get()) }
    factory { DeleteCategoriaUseCase(get()) }
    factory { GetAllCategoriaUseCase(get()) }

    factory { AddProductoUseCase(get()) }
    factory { GetProductoUseCase(get()) }
    factory { GetAllProductoUseCase(get()) }
    factory { UpdateProductoUseCase(get()) }
    factory { DeleteProductoUseCase(get()) }
}