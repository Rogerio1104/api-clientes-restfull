package br.com.senac.api.controller;

import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.repositorios.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
@CrossOrigin
public class ClientesController {

    @Autowired
    private ClientesRepository clientesRepository;

    @PostMapping("/")
    @CrossOrigin
    public ResponseEntity<Clientes> criarCliente(@RequestBody Clientes cliente){
     Clientes retorno =   clientesRepository.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }
    @GetMapping("/")
    @CrossOrigin
    public ResponseEntity<List<Clientes>> carregarClientes(){
        List<Clientes> retorno = clientesRepository.findAll();

        return ResponseEntity.ok().body(retorno);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clientesRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }
    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Clientes> atualizarCliente(@PathVariable Long id, @RequestBody Clientes cliente){

     Clientes retorno =   clientesRepository.findById(id).map(record ->{
            record.setNome(cliente.getNome());
            record.setSobreNome(cliente.getSobreNome());
            record.setTelefone(cliente.getTelefone());
            record.setDataNascimento(cliente.getDataNascimento());
            record.setEmail(cliente.getEmail());
            return clientesRepository.save(record);
        }).get();
     return ResponseEntity.ok(retorno);
    }
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Clientes> carregarClienteById(@PathVariable Long id){
       Clientes retorno = clientesRepository.findById(id).get();
        return ResponseEntity.ok(retorno);
    }
    @GetMapping("/{nome}/buscar")
    @CrossOrigin
    public ResponseEntity<List<Clientes>> carregarClienteByNome(@PathVariable String nome) {
        List<Clientes> retorno = clientesRepository.findByNome(nome);

        return ResponseEntity.ok(retorno);
    }
}
