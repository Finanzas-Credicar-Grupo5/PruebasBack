package com.upc.curso.interfaceservice;

import com.upc.curso.entidades.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> login(Long dni_usuario, String contraseña_usuario);
}
