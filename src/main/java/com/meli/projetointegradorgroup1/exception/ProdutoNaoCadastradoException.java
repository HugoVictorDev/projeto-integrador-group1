package com.meli.projetointegradorgroup1.exception;

public class ProdutoNaoCadastradoException extends RuntimeException{

    public ProdutoNaoCadastradoException(String mensagem){
        super(mensagem);
    }
}
