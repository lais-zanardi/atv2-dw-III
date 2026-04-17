package com.autobots.automanager.mapeador;

import com.autobots.automanager.dtos.requisicao.TelefoneRequisicaoDTO;
import com.autobots.automanager.dtos.resposta.TelefoneRespostaDTO;
import com.autobots.automanager.entidades.Telefone;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefoneMapeador {
    Telefone paraEntidade(TelefoneRequisicaoDTO requisicaoDTO);

    List<Telefone> paraEntidadeLista(List<TelefoneRequisicaoDTO> requisicaoDTO);

    TelefoneRespostaDTO paraResposta(Telefone telefone);

    List<TelefoneRespostaDTO> paraRespostaLista(List<Telefone> telefones);
}