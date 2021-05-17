package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client


//API ViaCep: https://viacep.com.br/ws/00000000/json/
@Client("https://viacep.com.br/ws")
interface EnderecoClient {

    @Get("/{cep}/json/", consumes = [MediaType.APPLICATION_JSON ])
    fun consulta(@QueryValue cep: String) : HttpResponse<EnderecoResponse>

}


