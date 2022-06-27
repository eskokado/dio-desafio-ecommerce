package com.dio.ecommerce.controllers;

import com.dio.ecommerce.models.Cliente;
import com.dio.ecommerce.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	@Operation(summary = "Listar todos clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "500", description = "Foi gerado um erro interno do servidor")
		}
	)
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		return ResponseEntity.ok(clienteService.buscarTodos());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obter um cliente pelo seu identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente recuperado com sucesso"),
			@ApiResponse(responseCode = "404", description = "O cliente não foi encontrado"),
			@ApiResponse(responseCode = "500", description = "Foi gerado um erro interno do servidor")
	}
	)
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(clienteService.buscarPorId(id));
	}

	@PostMapping
	@Operation(summary = "Criar um novo cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Criado com sucesso"),
			@ApiResponse(responseCode = "422", description = "Um erro de validação foi gerado"),
			@ApiResponse(responseCode = "500", description = "Foi gerado um erro interno do servidor")
	}
	)
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
		clienteService.inserir(cliente);
		return ResponseEntity.ok(cliente);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um cliente pelo seu identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "O cliente não foi encontrado"),
			@ApiResponse(responseCode = "500", description = "Foi gerado um erro interno do servidor")
	}
	)
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Excluir um cliente por seu identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
			@ApiResponse(responseCode = "404", description = "O cliente não foi encontrado"),
			@ApiResponse(responseCode = "500", description = "Foi gerado um erro interno do servidor")
	}
	)
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
