package com.upc.curso.services;

import com.upc.curso.dtos.UsuarioDTO;
import com.upc.curso.entidades.Usuario;
import com.upc.curso.interfaceservice.UsuarioService;
import com.upc.curso.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario register(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioDTO> listUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> login(Long dni_usuario, String contraseña_usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dni_usuario);

        return usuarioOptional.filter(usuario -> usuario.getContraseña_usuario().equals(contraseña_usuario));
    }


    private List<UsuarioDTO> convertToLisDto(List<Usuario> usuarios){
        return usuarios.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private UsuarioDTO convertToDto(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
