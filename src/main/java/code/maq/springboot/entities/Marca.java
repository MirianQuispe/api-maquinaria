package code.maq.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "marcas")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "marca")
	private List<Maquina> maquinas;

    public Marca() {
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }
}
