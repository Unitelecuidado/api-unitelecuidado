package org.unitelecuidado.api_rest.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioAtualizar;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioCadastro;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioCargo;
import org.unitelecuidado.api_rest.dto.usuario.UsuarioEspecialidade;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Data
@NoArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome não pode estar em branco!")
    private String nome;
    @NotBlank
    @Email(message = "O E-Mail deve ser valido!")
    private String email;
    @NotBlank (message = "A senha não pode estar em branco!")
    private String senha;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Um cargo deve ser selecionado!")
    private UsuarioCargo cargo;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Uma especialidade deve ser selecionada!")
    private UsuarioEspecialidade especialidade;
    private boolean ativo;

    public Usuario(UsuarioCadastro dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.cargo = dados.cargo();
        this.ativo = dados.ativo();
        this.especialidade = dados.especialidade();
    }

    public void atualizarDados(UsuarioAtualizar dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.email() != null){
            this.email = dados.email();
        }
        if(dados.senha() != null){
            this.senha = dados.senha();
        }
        if(dados.cargo() != null){
            this.cargo = dados.cargo();
        }
        if(dados.especialidade() != null) {
            this.especialidade = dados.especialidade();
        }
    }

    public void excluir(){
        this.ativo = false;
    }
}
