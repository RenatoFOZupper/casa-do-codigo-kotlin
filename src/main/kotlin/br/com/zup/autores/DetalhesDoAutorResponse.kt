package br.com.zup.autores

data class DetalhesDoAutorResponse(val autor: Autor) {

    val nome: String? = autor.nome
    val email: String? = autor.email
    val descricao: String? = autor.descricao

}
