package org.unitelecuidado.api_rest.dto.paciente;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum PacienteDesfecho {
    ATENDIDO,
    NAO_DISPONIVEL,
    NAO_LIGAR,
    NAO_ATENDEU_LIGACAO,
    TELEFONE_INCORRETO;
}
