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
    private List<String> errors;

    public ResponseDTO(T planet) {
        this.data = planet;
    }

    public ResponseDTO(List<String> errors) {
        this.errors = errors;
    }
}
