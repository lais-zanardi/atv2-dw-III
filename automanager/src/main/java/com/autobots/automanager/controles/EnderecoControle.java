package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.requisicao.EnderecoRequisicaoDTO;
import com.autobots.automanager.dtos.resposta.EnderecoRespostaDTO;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.mapeador.EnderecoMapeador;
import com.autobots.automanager.modelos.AdicionadorLinkEndereco;
import com.autobots.automanager.servicos.EnderecoServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoControle {
    private final EnderecoServico service;
    private final EnderecoMapeador mapeador;
    private final AdicionadorLinkEndereco addLink;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoRespostaDTO> obterPorId(@PathVariable Long id) {
        EnderecoRespostaDTO resposta =mapeador.paraResposta(service.encontrarPorId(id));
        addLink.adicionarLink(resposta);

        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping()
    public ResponseEntity<List<EnderecoRespostaDTO>> obterTodos() {
        List<EnderecoRespostaDTO> resposta = mapeador.paraRespostaLista(service.encontrarTodos());
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @PostMapping()
    public ResponseEntity<EnderecoRespostaDTO> cadastrar(@RequestBody @Valid EnderecoRequisicaoDTO dto) {
        Endereco endereco = mapeador.paraEntidade(dto);
        service.salvar(endereco);
        EnderecoRespostaDTO resposta = mapeador.paraResposta(endereco);
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnderecoRespostaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid EnderecoRequisicaoDTO atualizacao) {
        EnderecoRespostaDTO resposta = mapeador.paraResposta(service.atualizar(id, atualizacao));
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
