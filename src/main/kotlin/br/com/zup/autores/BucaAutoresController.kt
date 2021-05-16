package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import java.util.*
import javax.transaction.Transactional


@Controller(value = "/autores")
class BucaAutoresController(val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun lista(@QueryValue(defaultValue = "") email: String) : HttpResponse<Any> {

        if (email.isBlank()) {
            val autores: List<Autor> = autorRepository.findAll()

            val resposta: List<DetalhesDoAutorResponse> = autores.map { autor -> DetalhesDoAutorResponse(autor) }
            println("email em branco")

            return HttpResponse.ok(resposta)
        }

        val possivelAutor: Optional<Autor> = autorRepository.findByEmail(email)

        if (possivelAutor.isEmpty) {
            println("email nao encontrado")
            return HttpResponse.notFound()
        }

        val autor: Autor = possivelAutor.get()
        println("email encontrado")

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }
}