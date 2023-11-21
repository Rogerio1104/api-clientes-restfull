package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    @Query("select c from clientes c where nome like %:nome%")
    public List<Clientes> findByNome(@Param("nome") String nome);
}
