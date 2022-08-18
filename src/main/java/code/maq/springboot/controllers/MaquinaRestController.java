package code.maq.springboot.controllers;

import code.maq.springboot.entities.Maquina;
import code.maq.springboot.entities.Marca;
import code.maq.springboot.services.IMaquinaService;
import code.maq.springboot.services.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = {})
@RestController
@RequestMapping("api/maquina")
public class MaquinaRestController {

    @Autowired
    private IMaquinaService maquinaService;

    @Autowired
    private IMarcaService marcaService;

    @GetMapping
    public ResponseEntity<?> listarMaquinas(){
        return ResponseEntity.ok(maquinaService.listarTodas());
    }

    @GetMapping("/activas")
    public ResponseEntity<?> listarMaquinasActivas(){
        return ResponseEntity.ok(maquinaService.listarActivas());
    }

    @GetMapping("/{idMaquina}")
    public ResponseEntity<?> obtenerPorId(@PathVariable(name = "idMaquina") Long id){
        Optional<Maquina> maquinaOptional= maquinaService.porId(id);
        if (maquinaOptional.isPresent()){
            return ResponseEntity.ok(maquinaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Maquina maquina){
        Optional<Marca> marcaOptional=marcaService.porId(maquina.getMarca().getId());
        if (marcaOptional.isPresent()){
            maquina.setMarca(marcaOptional.get());
            Maquina maquinaDB = maquinaService.guardar(maquina);
            return ResponseEntity.status(HttpStatus.CREATED).body(maquinaDB);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{idMaquina}")
    public ResponseEntity<?> actualizar(@PathVariable(name = "idMaquina") Long id,@RequestBody Maquina maquina) {
        Optional<Maquina> maquinaOptional=maquinaService.porId(id);
        Optional<Marca> marcaOptional=marcaService.porId(maquina.getMarca().getId());
        if (maquinaOptional.isPresent() && maquinaOptional.isPresent()){
            maquina.setMarca(marcaOptional.get());
            Maquina maquinaDB= maquinaOptional.get();
            maquinaDB.setMarca(maquina.getMarca());
            maquinaDB.setAnio(maquina.getAnio());
            maquinaDB.setNombre(maquina.getNombre());
            maquinaDB.setModelo(maquina.getModelo());
            maquinaDB.setNumeroSerie(maquina.getNumeroSerie());
            maquinaDB.setNumeroPatente(maquina.getNumeroPatente());
            maquinaDB.setNumeroChasis(maquina.getNumeroChasis());
            maquinaDB.setNumeroMotor(maquina.getNumeroMotor());
            maquinaDB.setObservaciones(maquina.getObservaciones());
            maquinaDB.setFechaDeModificacion(new Date());
            maquinaService.guardar(maquinaDB);
            return ResponseEntity.status(HttpStatus.CREATED).body(maquinaDB);
        }
        return ResponseEntity.notFound().build();
    }



    @PutMapping("/estado/{idMaquina}")
    public ResponseEntity<?> cambiarEstado(@PathVariable(name = "idMaquina") Long id) {
        Optional<Maquina> maquinaOptional=maquinaService.porId(id);
        if (maquinaOptional.isPresent()){
            maquinaService.cambiarEstadoPorId(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idMaquina}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "idMaquina") Long id){
        Optional<Maquina> maquinaOptional=maquinaService.porId(id);
        if(maquinaOptional.isPresent()){
            maquinaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
