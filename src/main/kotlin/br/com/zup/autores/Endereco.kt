package br.com.zup.autores

import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse,
               val numero: String
) {

    val rua: String = enderecoResponse.logradouro
    val cidade: String = enderecoResponse.localidade
    val estado: String = enderecoResponse.uf

}
