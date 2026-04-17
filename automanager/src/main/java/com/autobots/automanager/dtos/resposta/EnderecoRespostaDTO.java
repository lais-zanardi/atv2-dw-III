package com.autobots.automanager.dtos.resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EnderecoRespostaDTO extends RepresentationModel<EnderecoRespostaDTO> {
    private Long id;
    private String rua;
    private String bairro;
    private String numero;
    private String cidade;
    private String estado;
    private String codigoPostal;
    private String informacoesAdicionais;
}