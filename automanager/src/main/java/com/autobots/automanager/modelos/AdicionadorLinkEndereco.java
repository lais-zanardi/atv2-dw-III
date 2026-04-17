package com.autobots.automanager.modelos;

import com.autobots.automanager.controles.EnderecoControle;
import com.autobots.automanager.dtos.resposta.EnderecoRespostaDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<EnderecoRespostaDTO> {

	@Override
	public void adicionarLink(EnderecoRespostaDTO objeto) {
		Long id = objeto.getId();
		Link linkProprio = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).obterPorId(id))
				.withSelfRel();

		Link linkAtualizar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).atualizar(id, null))
				.withRel("atualizar");

		Link linkExcluir = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).excluir(id))
				.withRel("excluir");

		Link linkLista = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).obterTodos())
				.withRel("enderecos");

		objeto.add(linkProprio, linkAtualizar, linkExcluir, linkLista);
	}

	@Override
	public void adicionarLink(List<EnderecoRespostaDTO> lista) {
		for (EnderecoRespostaDTO endereco : lista) {
			Long id = endereco.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).obterPorId(id))
					.withSelfRel();
			endereco.add(linkProprio);
		}
	}
}