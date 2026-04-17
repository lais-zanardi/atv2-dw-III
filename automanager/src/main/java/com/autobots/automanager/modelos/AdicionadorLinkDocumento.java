package com.autobots.automanager.modelos;

import com.autobots.automanager.controles.DocumentoControle;
import com.autobots.automanager.dtos.resposta.DocumentoRespostaDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<DocumentoRespostaDTO> {

	@Override
	public void adicionarLink(DocumentoRespostaDTO objeto) {
		Long id = objeto.getId();
		Link linkProprio = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DocumentoControle.class).obterPorId(id))
				.withSelfRel();

		Link linkAtualizar = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DocumentoControle.class).atualizar(id, null))
				.withRel("atualizar");

		Link linkExcluir = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DocumentoControle.class).excluir(id))
				.withRel("excluir");

		Link linkLista = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(DocumentoControle.class).obterTodos())
				.withRel("documentos");

		objeto.add(linkProprio, linkAtualizar, linkExcluir, linkLista);
	}

	@Override
	public void adicionarLink(List<DocumentoRespostaDTO> lista) {
		for (DocumentoRespostaDTO documento : lista) {
			Long id = documento.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder.methodOn(DocumentoControle.class).obterPorId(id))
					.withSelfRel();
			documento.add(linkProprio);
		}
	}
}