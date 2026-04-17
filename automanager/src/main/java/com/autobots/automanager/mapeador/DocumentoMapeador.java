package com.autobots.automanager.mapeador;

import com.autobots.automanager.dtos.requisicao.DocumentoRequisicaoDTO;
import com.autobots.automanager.dtos.resposta.DocumentoRespostaDTO;
import com.autobots.automanager.entidades.Documento;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentoMapeador {
    Documento paraEntidade(DocumentoRequisicaoDTO requisicaoDTO);

    List<Documento> paraEntidadeLista(List<DocumentoRequisicaoDTO> requisicaoDTO);

    DocumentoRespostaDTO paraResposta(Documento documento);

    List<DocumentoRespostaDTO> paraRespostaLista(List<Documento> documentos);
}