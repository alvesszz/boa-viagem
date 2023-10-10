package br.com.etechoracio.viagem.repository;

import br.com.etechoracio.viagem.entity.Gasto;
import br.com.etechoracio.viagem.entity.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {
    List<Viagem> findByDestinoIgnoreCase(String destino);

    @Query("select g from Gasto g where g.viagem.id = :idViagem")
    List<Gasto> findByGastos(Long idViagem);
}