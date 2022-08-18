package code.maq.springboot.services;

import code.maq.springboot.entities.Marca;

import java.util.List;
import java.util.Optional;

public interface IMarcaService {
    List<Marca> listar();
    Optional<Marca> porId(Long id);
    Marca guardar(Marca marca);
}
