package br.com.etechoracio.viagem.repository;

import br.com.etechoracio.viagem.entity.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {
    List<Viagem> findByDestino(String destino);
}