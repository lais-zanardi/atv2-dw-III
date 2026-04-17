package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.requisicao.ClienteRequisicaoDTO;
import com.autobots.automanager.dtos.resposta.ClienteRespostaDTO;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.mapeador.ClienteMapeador;
import com.autobots.automanager.modelos.AdicionadorLinkCliente;
import com.autobots.automanager.servicos.ClienteServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteControle {
	private final ClienteServico servico;
	private final ClienteMapeador mapeador;
	private final AdicionadorLinkCliente addLink;

	@GetMapping("/{id}")
	public ResponseEntity<ClienteRespostaDTO> obterPorId(@PathVariable Long id) {
		ClienteRespostaDTO resposta =servico.encontrarPorId(id);
		addLink.adicionarLink(resposta);
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}

	@GetMapping()
	public ResponseEntity<List<ClienteRespostaDTO>> obterTodos() {
		List<ClienteRespostaDTO> resposta = servico.encontrarTodos();
		addLink.adicionarLink(resposta);

		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}

	@PostMapping()
	public ResponseEntity<ClienteRespostaDTO> cadastrar(@RequestBody @Valid ClienteRequisicaoDTO dto) {
		Cliente cliente = mapeador.paraEntidade(dto);
		servico.salvar(cliente);
		ClienteRespostaDTO resposta = mapeador.paraResposta(cliente);
		addLink.adicionarLink(resposta);
		return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
	}


	@PatchMapping("/{id}")
	public ResponseEntity<ClienteRespostaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteRequisicaoDTO atualizacao) {
		ClienteRespostaDTO resposta = servico.atualizar(id, atualizacao);
		addLink.adicionarLink(resposta);
		return ResponseEntity.status(HttpStatus.OK).body(servico.atualizar(id, atualizacao));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
		servico.deletarPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
