package code.maq.springboot.repositories;

import code.maq.springboot.entities.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMaquinaRepository extends JpaRepository<Maquina, Long > {
    List<Maquina> findByEstadoTrue();
}
