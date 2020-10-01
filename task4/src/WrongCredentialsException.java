public class WrongCredentialsException extends Error{
    private String message;

    WrongCredentialsException(String message){
        super();
        this.message = message;
    }

    WrongCredentialsException(){
        super();
        this.message = "Введены неверные данные";
    }

    public String getMessage(){
        return message;
    }
}
