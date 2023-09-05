package banco.vit.vit.api.exceptions;

import banco.vit.vit.api.controller.DadosErro;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErros {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException e) {
        var erros = e.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }


    @ExceptionHandler(DadosErro.class)
    public ResponseEntity tratarErroRegrasDeNogocio(DadosErro e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError er) {
            this(er.getField(), er.getDefaultMessage());
        }
    }
}
