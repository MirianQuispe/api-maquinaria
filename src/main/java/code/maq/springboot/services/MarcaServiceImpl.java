package code.maq.springboot.services;

import code.maq.springboot.entities.Marca;
import code.maq.springboot.repositories.IMarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements IMarcaService{

    @Autowired
    private IMarcaRepository marcaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Marca> listar() {
        return marcaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Marca> porId(Long id) {
        return marcaRepository.findById(id);
    }

    @Override
    @Transactional
    public Marca guardar(Marca marca) {
        return marcaRepository.save(marca);
    }
}
