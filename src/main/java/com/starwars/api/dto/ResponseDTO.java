package com.starwars.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO<T> {
    private T data;
    private List<String> erros;

    public ResponseDTO(T planeta) {
        this.data = planeta;
    }

    public ResponseDTO(List<String> erros) {
        this.erros = erros;
    }
}
