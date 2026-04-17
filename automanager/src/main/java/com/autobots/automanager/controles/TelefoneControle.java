package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.requisicao.TelefoneRequisicaoDTO;
import com.autobots.automanager.dtos.resposta.TelefoneRespostaDTO;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.mapeador.TelefoneMapeador;
import com.autobots.automanager.modelos.AdicionadorLinkTelefone;
import com.autobots.automanager.servicos.TelefoneServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/telefones")
@RequiredArgsConstructor
public class TelefoneControle {
    private final TelefoneServico servico;
    private final TelefoneMapeador mapeador;
    private final AdicionadorLinkTelefone addLink;

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneRespostaDTO> obterPorId(@PathVariable Long id) {
        TelefoneRespostaDTO resposta =mapeador.paraResposta(servico.encontrarPorId(id));
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping()
    public ResponseEntity<List<TelefoneRespostaDTO>> obterTodos() {
        List<TelefoneRespostaDTO> resposta = mapeador.paraRespostaLista(servico.encontrarTodos());
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @PostMapping()
    public ResponseEntity<TelefoneRespostaDTO> cadastrar(@RequestBody @Valid TelefoneRequisicaoDTO dto) {
        Telefone entidade = mapeador.paraEntidade(dto);
        TelefoneRespostaDTO resposta = mapeador.paraResposta( servico.salvar(entidade));
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TelefoneRespostaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TelefoneRequisicaoDTO atualizacao) {
        TelefoneRespostaDTO resposta = mapeador.paraResposta( servico.atualizarPorId(id, atualizacao));
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servico.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
