package com.autobots.automanager.controles;

import com.autobots.automanager.dtos.requisicao.DocumentoRequisicaoDTO;
import com.autobots.automanager.dtos.resposta.DocumentoRespostaDTO;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.mapeador.DocumentoMapeador;
import com.autobots.automanager.modelos.AdicionadorLinkDocumento;
import com.autobots.automanager.servicos.DocumentoServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
@RequiredArgsConstructor
public class DocumentoControle {
    private final DocumentoServico servico;
    private final DocumentoMapeador mapeador;
    private final AdicionadorLinkDocumento addLink;


    @GetMapping("/{id}")
    public ResponseEntity<DocumentoRespostaDTO> obterPorId(@PathVariable Long id) {
        DocumentoRespostaDTO resposta =mapeador.paraResposta(servico.encontrarPorId(id));
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @GetMapping()
    public ResponseEntity<List<DocumentoRespostaDTO>> obterTodos() {
        List<DocumentoRespostaDTO> resposta = mapeador.paraRespostaLista(servico.encontrarTodos());
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @PostMapping()
    public ResponseEntity<DocumentoRespostaDTO> cadastrar(@RequestBody @Valid DocumentoRequisicaoDTO dto) {
        Documento entidade = mapeador.paraEntidade(dto);
        servico.salvar(entidade);
        DocumentoRespostaDTO resposta = mapeador.paraResposta(entidade);
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DocumentoRespostaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid DocumentoRequisicaoDTO atualizacao) {
        DocumentoRespostaDTO resposta = mapeador.paraResposta(servico.atualizar(id, atualizacao));
        addLink.adicionarLink(resposta);
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servico.deletarPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
