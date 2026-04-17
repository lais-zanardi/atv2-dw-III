package com.autobots.automanager.mapeador;

import com.autobots.automanager.dtos.requisicao.EnderecoRequisicaoDTO;
import com.autobots.automanager.dtos.resposta.EnderecoRespostaDTO;
import com.autobots.automanager.entidades.Endereco;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapeador {
    Endereco paraEntidade(EnderecoRequisicaoDTO requisicaoDTO);

    List<Endereco> paraEntidadeLista(List<EnderecoRequisicaoDTO> requisicaoDTO);

    EnderecoRespostaDTO paraResposta(Endereco endereco);

    List<EnderecoRespostaDTO> paraRespostaLista(List<Endereco> enderecos);
}