package br.com.erudio.exceptions.handler;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjetIsNullException extends RuntimeException{
    private  static final long serialVersionUID = 1l;
    public RequiredObjetIsNullException() {
        super("It is not allowed to persist a null object(Não é permitido persistir objeto nulo.");
    }
    public RequiredObjetIsNullException(String ex) {
        super(ex);
    }
}
