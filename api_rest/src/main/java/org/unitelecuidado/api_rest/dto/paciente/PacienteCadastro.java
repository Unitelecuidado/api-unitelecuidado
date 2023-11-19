package org.unitelecuidado.api_rest.dto.paciente;

import io.swagger.v3.oas.annotations.media.Schema;

public record PacienteCadastro(

        String nome,
        String telefone,
        PacienteDesfecho desfecho,
        String ultima_alteracao,
        String origem,
        PacienteStatus status,
        boolean ativo
) {
}
