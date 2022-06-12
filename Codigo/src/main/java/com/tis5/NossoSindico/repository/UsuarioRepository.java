package com.tis5.NossoSindico.repository;

import com.tis5.NossoSindico.domain.Usuario;
import com.tis5.NossoSindico.domain.UsuarioComApto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
}
