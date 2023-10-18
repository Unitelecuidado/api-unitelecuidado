package org.unitelecuidado.api_rest.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unitelecuidado.api_rest.dto.paciente.PacienteCadastro;
import org.unitelecuidado.api_rest.dto.paciente.PacienteDesfecho;
@Table(name = "pacientes")
@Entity(name = "Paciente")
@Data
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome não pode estar em branco!")
    private String nome;
    private String cpf;
    @NotBlank(message = "O telefone não pode estar em branco!")
    private String telefone;
    private PacienteDesfecho desfecho;
    private String observações;
    private String detalhes;
    private boolean ativo;

    public Paciente(PacienteCadastro dados){
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.ativo = dados.ativo();
    }


}
