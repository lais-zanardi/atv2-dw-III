package com.autobots.automanager.dtos.resposta;

import com.autobots.automanager.dtos.requisicao.DocumentoRequisicaoDTO;
import com.autobots.automanager.dtos.requisicao.EnderecoRequisicaoDTO;
import com.autobots.automanager.dtos.requisicao.TelefoneRequisicaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

@Data @EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRespostaDTO extends RepresentationModel<ClienteRespostaDTO> {
    private Long id;
    private String nome;
    private String nomeSocial;
    private Date dataNascimento;
    private Date dataCadastro;
    private EnderecoRequisicaoDTO endereco;
    private DocumentoRequisicaoDTO documento;
    private List<TelefoneRequisicaoDTO> telefones;
}
