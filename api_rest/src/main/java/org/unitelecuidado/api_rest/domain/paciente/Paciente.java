package org.unitelecuidado.api_rest.domain.paciente;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unitelecuidado.api_rest.dto.paciente.*;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Data
@NoArgsConstructor
@Schema(description = "Modelos relacionados a Pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do paciente")
    private Long id;
    @NotBlank(message = "O nome não pode estar em branco!")
    @Schema(description = "Nome do paciente")
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
    private String ultima_alteracao;
    private String origem;
    private String cns;
    private String endereco;
    @Enumerated(EnumType.STRING)
    private PacienteStatus status;

    public Paciente(PacienteCadastro dados){
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.desfecho = dados.desfecho();
        this.ativo = dados.ativo();
        this.ultima_alteracao = dados.ultima_alteracao();
        this.origem = dados.origem();
        this.status = dados.status();
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
        if(dados.ultima_alteracao() != null){
            this.ultima_alteracao = dados.ultima_alteracao();
        }
        if(dados.origem() != null){
            this.origem = dados.origem();
        }
        if (dados.cns() != null){
            this.cns = dados.cns();
        }
        if (dados.endereco() != null){
            this.endereco = dados.endereco();
        }
        if (dados.status() != null){
            this.status = dados.status();
        }
    }

    public void excluir(){
        this.ativo = false;
    }




}
