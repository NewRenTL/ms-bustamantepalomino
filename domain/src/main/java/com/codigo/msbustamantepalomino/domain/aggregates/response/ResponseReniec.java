package com.codigo.msbustamantepalomino.domain.aggregates.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseReniec {
    private String nombres;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String tipoDocumento;
    private String numeroDocumento;
    private String digitoVerificador;
}
