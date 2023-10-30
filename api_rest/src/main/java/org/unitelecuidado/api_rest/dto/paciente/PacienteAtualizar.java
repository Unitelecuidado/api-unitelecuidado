package org.unitelecuidado.api_rest.dto.paciente;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PacienteAtualizar(
        @Id
        @NotNull
        Long id,
        String nome,
        String cpf,
        String telefone,
        PacienteDesfecho desfecho,
        String observacoes,
        String detalhes,
        String sexo,
        String nascimento,
        PacienteEncaminhamento encaminhamento

) {
}
