package br.com.zup.categorias

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller(value = "/categorias")
class CadastraCategoria(val categoriaRepository: CategoriaRepository) {

    @Post
    fun cadastra(@Body @Valid request: NovaCategoriaRequest) {
        println("request => $request")
        val categoria = request.paraCategoria()
        categoriaRepository.save(categoria)
        println("Categoria => ${categoria.nome}")
    }

}