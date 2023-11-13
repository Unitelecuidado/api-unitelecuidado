package org.unitelecuidado.api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unitelecuidado.api_rest.domain.paciente.Paciente;
import org.unitelecuidado.api_rest.domain.usuario.Usuario;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByAtivoTrue();

    List<Paciente> findAllByAtivoFalse();
}
