package org.unitelecuidado.api_rest.dto.usuario;

public record UsuarioCadastro(
        String nome,
        String email,
        String senha,
        UsuarioCargo cargo,
        boolean ativo
) {
}
