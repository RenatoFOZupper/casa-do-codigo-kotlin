package br.com.zup.categorias

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
data class NovaCategoriaRequest(@field:NotBlank val nome: String
) {
    fun paraCategoria() : Categoria {
        return Categoria(nome = nome)
    }

}
