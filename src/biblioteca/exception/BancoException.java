/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.exception;

/**
 *
 * @author Bianca
 */
public class BancoException extends Exception{
   
    public BancoException() {
        super("Exceção geral em Banco");
    }

    public BancoException(String message) {
        super(message);
    }

    public BancoException(String message, Throwable cause) {
        super(message, cause);
    }

    public BancoException(Throwable cause) {
        super(cause);
    }
    
    
}
