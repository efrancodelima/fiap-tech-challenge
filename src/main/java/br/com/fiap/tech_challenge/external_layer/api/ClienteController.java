package br.com.fiap.tech_challenge.external_layer.api;

import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.ports.in.ClienteServicePort;
import br.com.fiap.tech_challenge.interface_layer.controller.mapper.ClienteDTOMapper;
import br.com.fiap.tech_challenge.interface_layer.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.interface_layer.controller.model.request.CadastrarClienteDTO;
import br.com.fiap.tech_challenge.interface_layer.controller.swagger.ClienteSwaggerInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/cliente")
public class ClienteController implements ClienteSwaggerInterface {

    private final ClienteServicePort service;
    private final ClienteDTOMapper clienteDTOMapper;

    @Autowired
    public ClienteController(ClienteServicePort service, ClienteDTOMapper clienteDTOMapper) {
        this.service = service;
        this.clienteDTOMapper = clienteDTOMapper;
    }

    @Override
    public ResponseEntity<String> cadastrarCliente(@RequestBody CadastrarClienteDTO cadastrar) {
        Cliente cliente = clienteDTOMapper.cadastrarToCliente(cadastrar);
        service.cadastrarCliente(cliente);
        return new ResponseEntity<>("Cliente cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cliente> buscarClientePorCPF(@PathVariable String cpf) {
        Cliente cliente = service.buscarClientePorCPF(cpf);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody AtualizarClienteDTO atualizar) {
        Cliente cliente = clienteDTOMapper.atualizarToCliente(atualizar);
        service.atualizarCliente(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> excluirCliente (@PathVariable Long id) {
        return new ResponseEntity<>(service.excluirCliente(id), HttpStatus.OK);
    }

}
