package com.upc.curso.controller;

import com.upc.curso.dtos.UsuarioDTO;
import com.upc.curso.entidades.Usuario;
import com.upc.curso.services.UsuarioServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class Controlador {
    @Autowired //inyectando
    private UsuarioServiceImpl usuarioServiceImpl;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = convertToEntity(usuarioDTO);
        usuario = usuarioServiceImpl.register(usuario);
        usuarioDTO = convertToDto(usuario);
        return new ResponseEntity<UsuarioDTO>(usuarioDTO, HttpStatus.OK);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> listUsuario() {
        try {
            List<UsuarioDTO> usuarios = usuarioServiceImpl.listUsuarios();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            // Realiza la lógica de autenticación aquí
            Optional<Usuario> usuario = usuarioServiceImpl.login(usuarioDTO.getDni_usuario(), usuarioDTO.getContraseña_usuario());

            if (usuario.isPresent()) {
                // Si las credenciales son válidas, devuelve el Propietario
                UsuarioDTO responseDTO = convertToDto(usuario.get());
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                // Si las credenciales son inválidas, devuelve un ResponseEntity con HttpStatus.UNAUTHORIZED
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/calcular")
    public float calcularVanConFlujoContinuo(@RequestParam float prestamo,
                                             @RequestParam float cok,
                                             @RequestParam float flujo,
                                             @RequestParam float tiempo) {
        return calcularVanConFlujoContinuo(prestamo, cok, flujo, tiempo);
    }

    @GetMapping("/calcularget")
    public float calcularVanConFlujoContinuoGet(@RequestParam float prestamo,
                                                @RequestParam float cok,
                                                @RequestParam float flujo,
                                                @RequestParam float tiempo) {
        return calcularVanConFlujoContinuo(prestamo, cok, flujo, tiempo);
    }

    @PostMapping("/convertirTiempo")
    public float convertirTiempoTasaNominal(@RequestParam float tasa,
                                            @RequestParam String periodo) {
        return convertirTiempoTasaNominal(tasa, periodo);
    }

    @GetMapping("/convertirTiempoget")
    public float convertirTiempoTasaNominalGet(@RequestParam float tasa,
                                               @RequestParam String periodo) {
        return convertirTiempoTasaNominal(tasa, periodo);
    }

    @PostMapping("/convertir")
    public float convertirTasaDeSeguroMensual(@RequestParam float tasa,
                                              @RequestParam String periodo) {
        return convertirTasaDeSeguroMensual(tasa, periodo);
    }

    @GetMapping("/convertirget")
    public float convertirTasaDeSeguroMensualGet(@RequestParam float tasa,
                                                 @RequestParam String periodo) {
        return convertirTasaDeSeguroMensual(tasa, periodo);
    }

    @PostMapping("/convertirtiempo")
    public float convertirAlTiempoDeLaTasa(@RequestParam float tiempo,
                                           @RequestParam String periodo) {
        return convertirAlTiempoDeLaTasa(tiempo, periodo);
    }

    @GetMapping("/convertirtiempoget")
    public float convertirAlTiempoDeLaTasaGet(@RequestParam float tiempo,
                                              @RequestParam String periodo) {
        return convertirAlTiempoDeLaTasa(tiempo, periodo);
    }

    @PostMapping("/cambioPeriodo")
    public float cambioPeriodoTasaEfectiva(@RequestParam float tasa,
                                           @RequestParam String periodo) {
        return cambioPeriodoTasaEfectiva(tasa, periodo);
    }

    @GetMapping("/cambioPeriodoget")
    public float cambioPeriodoTasaEfectivaGet(@RequestParam float tasa,
                                              @RequestParam String periodo) {
        return cambioPeriodoTasaEfectiva(tasa, periodo);
    }

    @PostMapping("/cambioTasa")
    public float cambioTasaNominalAEfectiva(@RequestParam float tasa,
                                            @RequestParam String periodo) {
        return cambioTasaNominalAEfectiva(tasa, periodo);
    }

    @GetMapping("/cambioTasaget")
    public float cambioTasaNominalAEfectivaGet(@RequestParam float tasa,
                                               @RequestParam String periodo) {
        return cambioTasaNominalAEfectiva(tasa, periodo);
    }

    @PostMapping("/calcularcuota")
    public float calcularCuotaInicial(@RequestParam float porcentaje,
                                      @RequestParam float precio) {
        return calcularCuotaInicial(porcentaje, precio);
    }

    @GetMapping("/calcularcuotaget")
    public float calcularCuotaInicialGet(@RequestParam float porcentaje,
                                         @RequestParam float precio) {
        return calcularCuotaInicial(porcentaje, precio);
    }

    @PostMapping("/obtener")
    public float obtenerTasaDeInteres(@RequestParam float tasa,
                                      @RequestParam String tipoTasa,
                                      @RequestParam String periodo) {
        return obtenerTasaDeInteres(tasa, tipoTasa, periodo);
    }

    @GetMapping("/obtenerget")
    public float obtenerTasaDeInteresGet(@RequestParam float tasa,
                                         @RequestParam String tipoTasa,
                                         @RequestParam String periodo) {
        return obtenerTasaDeInteres(tasa, tipoTasa, periodo);
    }

    @PostMapping("/calcularcuotatotal")
    public float calcularCuotaTotal(@RequestParam float prestamo,
                                    @RequestParam float TE,
                                    @RequestParam float TSD,
                                    @RequestParam float tiempo) {
        return calcularCuotaTotal(prestamo, TE, TSD, tiempo);
    }

    @GetMapping("/calcularcuotatotalget")
    public float calcularCuotaTotalGet(@RequestParam float prestamo,
                                       @RequestParam float TE,
                                       @RequestParam float TSD,
                                       @RequestParam float tiempo) {
        return calcularCuotaTotal(prestamo, TE, TSD, tiempo);
    }

    private UsuarioDTO convertToDto(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

    private List<UsuarioDTO> convertToLisDto(List<Usuario> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
