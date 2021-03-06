package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put
import java.util.*
import javax.inject.Inject
import javax.transaction.Transactional

@Controller(value = "/autores/{id}")
class AtualizaAutorController(@Inject val autorRepository: AutorRepository) {

    @Put
    @Transactional
    fun atualiza(@PathVariable id: Long, descricao: String): HttpResponse<Any> {

        val possivelAutor: Optional<Autor> = autorRepository.findById(id)
        if (possivelAutor.isEmpty) { return HttpResponse.notFound() }

        val autor = possivelAutor.get()
        autor.descricao = descricao
        //autorRepository.update(autor)

        return HttpResponse.created(DetalhesDoAutorResponse(autor))
    }
}