package com.upc.curso.repositories;

import com.upc.curso.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {


    @Query("SELECT p FROM Usuario p WHERE p.dni_usuario = :dni")
    Usuario findByDni_usuario(@Param("dni") String dni);
}
