package ies.sequeros.dam.ad.orm.endpoints

import ies.sequeros.dam.ad.orm.application.productos.AddProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.DeleteProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.GetAllProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.GetProductoUseCase
import ies.sequeros.dam.ad.orm.application.productos.commands.AddProductoCommand

import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.parameters
import io.ktor.server.plugins.BadRequestException
import io.ktor.server.request.receive
import io.ktor.server.request.receiveMultipart
import io.ktor.server.request.uri
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.configureProductosRoutes() {
    route("/productos") {
        get {
            val useCase by inject<GetAllProductoUseCase>()
            val items = useCase()
            call.respond(items)
        }
        post {
            val useCase by inject < AddProductoUseCase>()
            val command = call.receive<AddProductoCommand>()
            var item = useCase(command)
            val location = "${call.request.uri}/${item.id}"
            call.response.header(HttpHeaders.Location, location)
            call.respond(HttpStatusCode.Created, item)
        }
        get("/{id}") {
            val useCase by inject<GetProductoUseCase>()
            val stringId = call.parameters["id"] ?: throw BadRequestException("El parámetro id es obligatorio")

            val id = UUID.fromString(stringId)
            val cat = useCase(id)
            if (cat != null) {
                call.respond(HttpStatusCode.OK, cat)
            } else {
                call.respond(HttpStatusCode.NotFound, "Producto no encontrado")
            }

        }
        delete("/{id}") {
            val useCase by inject<DeleteProductoUseCase>()
            val stringId = call.parameters["id"] ?: throw BadRequestException("El parámetro id es obligatorio")
            val id = UUID.fromString(stringId)
            val cat = useCase(id)
            call.respond(HttpStatusCode.OK, cat)
        }
    }
}