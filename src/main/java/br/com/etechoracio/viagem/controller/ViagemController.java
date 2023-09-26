package br.com.etechoracio.viagem.controller;

//Ana Carolina e Kaleb
import br.com.etechoracio.viagem.entity.Viagem;
import br.com.etechoracio.viagem.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemRepository repository;

    @GetMapping
    public List<Viagem> buscarTodos(@RequestParam(required = false) String destino){
        if(destino==null)
            return repository.findAll();
        else
            return repository.findByDestino(destino);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Viagem> existe = repository.findById(id);
        if(existe.isPresent()){
            return ResponseEntity.ok(existe);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Viagem inserir(@RequestBody Viagem body){
        Viagem inserida = repository.save(body);
        return inserida;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viagem> atualizar(@PathVariable Long id,
                            @RequestBody Viagem obj){
        Optional<Viagem> existe = repository.findById(id);
        if(existe.isPresent()){
            repository.save(obj);
            return ResponseEntity.ok(obj);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){
        Optional<Viagem> existe = repository.findById(id);
        if(existe.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

}
