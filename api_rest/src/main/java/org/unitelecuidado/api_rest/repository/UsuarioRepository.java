package org.unitelecuidado.api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unitelecuidado.api_rest.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
