package org.unitelecuidado.api_rest.dto.usuario;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public record UsuarioAtualizar(
        @Id
        @NotNull
        Long id,
        String nome,
        String email,
        String senha,
        UsuarioCargo cargo
) {
}
