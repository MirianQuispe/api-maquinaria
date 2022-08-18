package code.maq.springboot.controllers;

import code.maq.springboot.entities.Maquina;
import code.maq.springboot.entities.Marca;
import code.maq.springboot.services.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {})
@RestController
@RequestMapping("api/marca")
public class MarcaRestController {
    @Autowired
    private IMarcaService marcaService;

    @GetMapping
    public ResponseEntity<?> listarMarcas(){
        return ResponseEntity.ok(marcaService.listar());
    }

    @GetMapping("/{idMarca}")
    public ResponseEntity<?> obtenerPorId(@PathVariable(name = "idMarca") Long id){
        Optional<Marca> marcaOptional= marcaService.porId(id);
        if (marcaOptional.isPresent()){
            return ResponseEntity.ok(marcaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Marca marca){
        Marca marcaDB = marcaService.guardar(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaDB);
    }
}
