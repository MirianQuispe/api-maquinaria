package code.maq.springboot.services;

import code.maq.springboot.entities.Maquina;

import java.util.List;
import java.util.Optional;

public interface IMaquinaService {
    List<Maquina> listarTodas();
    List<Maquina> listarActivas();
    Optional<Maquina> porId(Long id);
    Maquina guardar(Maquina maquina);
    void cambiarEstadoPorId(Long id);
    void eliminar(Long id);
}
