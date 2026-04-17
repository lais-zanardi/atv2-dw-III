package com.autobots.automanager.modelos;

import com.autobots.automanager.controles.TelefoneControle;
import com.autobots.automanager.dtos.resposta.TelefoneRespostaDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<TelefoneRespostaDTO> {

	@Override
	public void adicionarLink(TelefoneRespostaDTO objeto) {
		Long id = objeto.getId();
		Link linkProprio = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).obterPorId(id))
				.withSelfRel();

		Link linkAtualizar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).atualizar(id, null))
				.withRel("atualizar");

		Link linkExcluir = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).excluir(id))
				.withRel("excluir");

		Link linkLista = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).obterTodos())
				.withRel("telefones");

		objeto.add(linkProprio, linkAtualizar, linkExcluir, linkLista);
	}

	@Override
	public void adicionarLink(List<TelefoneRespostaDTO> lista) {
		for (TelefoneRespostaDTO telefone : lista) {
			Long id = telefone.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(TelefoneControle.class).obterPorId(id))
					.withSelfRel();
			telefone.add(linkProprio);
		}
	}
}