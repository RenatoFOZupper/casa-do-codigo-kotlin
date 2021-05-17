package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional

@Controller(value = "/autores/{id}")
class DeletaAutorController(@Inject val autorRepository: AutorRepository) {

    @Delete
    @Transactional
    fun deleta(@PathVariable id: Long) : HttpResponse<Any> {
        val possivelAutor: Optional<Autor> = autorRepository.findById(id)
        if (possivelAutor.isEmpty) { return HttpResponse.notFound() }

        val autor: Autor = possivelAutor.get()
        autorRepository.delete(autor)

        return HttpResponse.ok()
    }
}