import java.io.IOException;

/** Интерфейс управления аккаунтами, для базы используется
 * текстовый файл в формате CSV */
public interface MailAccountManager {

    /**
     * Метод проверяет, что данный аккаунт в базе отсутствует, и
     * создает новую запись, в противном случае выбрасывает ошибку
     * DuplicateAccountException
     */
    void registerNewAccount(String email, String password, Person person)
            throws DuplicateAccountException, IOException;

    /**
     * Метод удаляет пользователя, если логин и пароль введены
     * верно. В противном случае выбрасывает
     * ошибку WrongCredentialsException
     */
    void removeAccount(String email, String password) throws WrongCredentialsException, IOException;

    /**
     * Метод возвращает true если пользователь с данным логином
     * существует
     */
    boolean hasAccount(String email) throws IOException;

    /**
     * Метод возвращает Person, если логин и пароль совпадают, иначе
     * выбрасывает исключение WrongCredentialsException.
     * Если неудачных попыток авторизоваться больше 5, то
     * выбрасывается исключение TooManyLoginAttemptsException
     */
    Person getPerson(String email, String password) throws
            TooManyLoginAttemptsException, IOException, WrongCredentialsException;

    /**
     * Метод возвращает кол-во аккаунтов в базе
     */
    int numOfAccounts() throws IOException;

}