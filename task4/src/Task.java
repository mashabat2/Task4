import java.io.IOException;

public class Task {
    public static void main(String[] args) {
        // create people
        Person name1 = new Person("Masha", "26.09.2000");
        Person name2 = new Person("Kate", "15.02.1994");

        // create manager
        AccountManagerImpl man = new AccountManagerImpl();
        try{
            man.registerNewAccount("mashabat2@mail.ru", "qwerty", name1);
            // create a mistake
            man.registerNewAccount("mashabat2@mail.ru", "qwerty", name1);
            man.registerNewAccount("kategdg@mail.ru", "qvfdty", name2);
                    }
        catch (DuplicateAccountException|IOException error){
            error.printStackTrace();
        }

        // delete account
        try {
            man.removeAccount("kategdg@mail.ru","qvfdty" );
            // mistake
            man.removeAccount("sg@mail.ru","qvfdty" );
        } catch (WrongCredentialsException | IOException e){
            System.out.println(e.getMessage());
        }

        // has account or not
        try {
            System.out.println("Аккаунт существует/нет: " + man.hasAccount("mashabat2@mail.ru"));
        }catch(IOException e){
            e.printStackTrace();
        }

        // how many accounts
        try {
            System.out.println("Аккаунтов: " + man.numOfAccounts() + "шт.");
        }catch (IOException e){
            e.printStackTrace();
        }

        // try to enter
        try {
            System.out.println(man.getPerson("mashabat2@mail.ru", "qwerty"));
        }catch (WrongCredentialsException | TooManyLoginAttemptsException | IOException e){
            System.out.println(e.getMessage());
        }
        try{
            System.out.println(man.getPerson("mbat2@mail.ru", "qwerty"));
        }catch (WrongCredentialsException | TooManyLoginAttemptsException | IOException e){
            System.out.println(e.getMessage());
        }

    }
}