package org.unitelecuidado.api_rest.dto.paciente;

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
