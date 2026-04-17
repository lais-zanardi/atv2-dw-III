package com.autobots.automanager.modelos;

import com.autobots.automanager.controles.ClienteControle;
import com.autobots.automanager.dtos.resposta.ClienteRespostaDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkCliente implements AdicionadorLink<ClienteRespostaDTO> {

	@Override
	public void adicionarLink(ClienteRespostaDTO objeto) {
		Long id = objeto.getId();
		Link linkProprio = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).obterPorId(id))
				.withSelfRel();

		Link linkAtualizar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).atualizar(id, null))
				.withRel("atualizar");

		Link linkExcluir = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).excluirCliente(id))
				.withRel("excluir");

		Link linkLista = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).obterTodos())
				.withRel("clientes");

		objeto.add(linkProprio, linkAtualizar, linkExcluir, linkLista);
	}

	@Override
	public void adicionarLink(List<ClienteRespostaDTO> lista) {
		for (ClienteRespostaDTO cliente : lista) {
			Long id = cliente.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).obterPorId(id))
					.withSelfRel();
			cliente.add(linkProprio);
		}
	}
}