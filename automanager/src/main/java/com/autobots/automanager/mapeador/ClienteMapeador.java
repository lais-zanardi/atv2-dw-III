package com.autobots.automanager.mapeador;

import com.autobots.automanager.dtos.requisicao.ClienteRequisicaoDTO;
import com.autobots.automanager.dtos.resposta.ClienteRespostaDTO;
import com.autobots.automanager.entidades.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapeador {
    Cliente paraEntidade(ClienteRequisicaoDTO requisicaoDTO);

    List<Cliente> paraEntidadeLista(List<ClienteRequisicaoDTO> requisicaoDTO);

    ClienteRespostaDTO paraResposta(Cliente cliente);

    List<ClienteRespostaDTO> paraRespostaLista(List<Cliente> clientes);
}