package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller(value = "/autores")
class CadastraAutorController(@Inject val autorRepository: AutorRepository,
                              @Inject val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest) : HttpResponse<Any> {
        println("request => $request")

        // fazer uma requisicao para um serviço externo
        val enderecoResponse: HttpResponse<EnderecoResponse> = enderecoClient.consulta(request.cep)
        if (enderecoResponse.body() == null) { return HttpResponse.badRequest() }

        val autor = request.paraAutor(enderecoResponse.body()!!)

        println("Autor => ${autor.nome}")
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autores/{id}")
                            .expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
    }

}