package code.maq.springboot.repositories;

import code.maq.springboot.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMarcaRepository extends JpaRepository<Marca,Long> {
}
