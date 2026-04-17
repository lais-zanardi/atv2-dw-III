package com.autobots.automanager.dtos.resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor @EqualsAndHashCode
public class DocumentoRespostaDTO extends RepresentationModel<DocumentoRespostaDTO> {
    private Long id;
    private String tipo;
    private String numero;
}
