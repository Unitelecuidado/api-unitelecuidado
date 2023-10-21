package org.unitelecuidado.api_rest.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unitelecuidado.api_rest.dto.paciente.PacienteAtualizar;
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
    @Enumerated(EnumType.STRING)
    private PacienteDesfecho desfecho;
    private String observacoes;
    private String detalhes;
    private boolean ativo;

    public Paciente(PacienteCadastro dados){
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.desfecho = dados.desfecho();
        this.ativo = dados.ativo();
    }

    public void atualizarDados(PacienteAtualizar dados){
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.cpf() != null){
            this.cpf = dados.cpf();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if(dados.desfecho() != null){
            this.desfecho = dados.desfecho();
        }
        if(dados.observacoes() != null){
            this.observacoes = dados.observacoes();
        }
        if(dados.detalhes() != null){
            this.detalhes = dados.detalhes();
        }

    }

    public void excluir(){
        this.ativo = false;
    }




}
