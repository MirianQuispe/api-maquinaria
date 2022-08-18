package code.maq.springboot.services;

import code.maq.springboot.entities.Maquina;
import code.maq.springboot.repositories.IMaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaServiceImpl implements IMaquinaService{

    @Autowired
    private IMaquinaRepository maquinaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Maquina> listarTodas() {
        return maquinaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maquina> listarActivas() {
        return maquinaRepository.findByEstadoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Maquina> porId(Long id) {
        return maquinaRepository.findById(id);
    }

    @Override
    @Transactional
    public Maquina guardar(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    @Override
    @Transactional
    public void cambiarEstadoPorId(Long id) {
        Optional<Maquina> optionalMaquina = this.porId(id);
        if (optionalMaquina.isPresent()){
            Maquina maquinaDB= optionalMaquina.get();
            if (maquinaDB.isEstado()) {
                maquinaDB.setEstado(false);
            } else  maquinaDB.setEstado(true);
            maquinaRepository.save(maquinaDB);
        }
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        maquinaRepository.deleteById(id);
    }
}
