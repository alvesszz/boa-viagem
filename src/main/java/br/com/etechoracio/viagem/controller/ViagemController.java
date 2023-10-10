//allan e Fabia
package br.com.etechoracio.viagem.controller;


import br.com.etechoracio.viagem.entity.Gasto;
import br.com.etechoracio.viagem.entity.Viagem;
import br.com.etechoracio.viagem.exceptions.ViagemInvalidaException;
import br.com.etechoracio.viagem.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
            return repository.findByDestinoIgnoreCase(destino);
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
                            @RequestBody Viagem viagem){
        Optional<Viagem> existe = repository.findById(id);
        if(existe.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(existe.get().getDataSaida()!= null){
            throw new ViagemInvalidaException("Viagem finalizada não permite atualização");
        }
        Viagem salva = repository.save(viagem);
        return ResponseEntity.ok(salva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){
        Optional<Viagem> existe = repository.findById(id);
        if(existe.isPresent()) {
            List<Gasto> gastos = repository.findByGastos(id);
            if(!gastos.isEmpty()){
                throw new IllegalArgumentException("Existe gastos para a viagem informada");
            }
            repository.deleteById(id);
            return ResponseEntity.ok().build();

        }
            return ResponseEntity.notFound().build();
    }

}
