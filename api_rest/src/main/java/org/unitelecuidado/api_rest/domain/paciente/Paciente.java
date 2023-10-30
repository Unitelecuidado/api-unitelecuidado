package org.unitelecuidado.api_rest.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unitelecuidado.api_rest.dto.paciente.PacienteAtualizar;
import org.unitelecuidado.api_rest.dto.paciente.PacienteCadastro;
import org.unitelecuidado.api_rest.dto.paciente.PacienteDesfecho;
import org.unitelecuidado.api_rest.dto.paciente.PacienteEncaminhamento;

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
    @Enumerated(EnumType.STRING)
    private PacienteEncaminhamento encaminhamento;
    private String sexo;
    private String nascimento;

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
        if(dados.sexo() != null){
            this.sexo = dados.sexo();
        }
        if(dados.encaminhamento() != null){
            this.encaminhamento = dados.encaminhamento();
        }
        if(dados.nascimento() != null){
            this.nascimento = dados.nascimento();
        }

    }

    public void excluir(){
        this.ativo = false;
    }




}
