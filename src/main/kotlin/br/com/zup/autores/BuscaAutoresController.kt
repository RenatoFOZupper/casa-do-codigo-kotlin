package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import org.hibernate.annotations.FetchProfile
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional


@Controller(value = "/autores")
class BuscaAutoresController(@Inject val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun lista(@QueryValue(defaultValue = "") email: String) : HttpResponse<Any> {

        if (email.isBlank()) {
            val autores: List<Autor> = autorRepository.findAll()

            val resposta: List<DetalhesDoAutorResponse> = autores.map { autor -> DetalhesDoAutorResponse(autor) }

            return HttpResponse.ok(resposta)
        }

        val possivelAutor: Optional<Autor> = autorRepository.findByEmail(email)

        if (possivelAutor.isEmpty) { return HttpResponse.notFound() }

        val autor: Autor = possivelAutor.get()
        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }
}