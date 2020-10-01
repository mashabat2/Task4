import java.io.*;
import java.util.ArrayList;
import java.util.List;

class AccountManagerImpl implements MailAccountManager{
    AttemptCounter numbers = AttemptCounter.getInstance();

    @Override
    public void registerNewAccount(String email, String password, Person name) throws DuplicateAccountException, IOException {
        // читает текст из потока ввода символов
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Program Files\\task4.1\\task4\\src\\base.csv"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Program Files\\task4.1\\task4\\src\\base.csv", true));) {
            String str;
            // проверяем построчно на наличие аккаунта
            while ((str = reader.readLine()) != null) {
                if (str.contains(name.getName())) {
                    throw new DuplicateAccountException("Аккаунт уже создан.");
                }
            }
            writer.write(name.toString() + ", " + email + ", " + password + "\n");
        }
    }

    /*
        Удаление аккаунта.
        deleted - принимает значение того человека, данные которого нужно удалеить из списка names
     */
    @Override
    public void removeAccount(String email, String password) throws WrongCredentialsException, IOException {
        BufferedWriter writer = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Program Files\\task4.1\\task4\\src\\base.csv"));){
            List<String> names = new ArrayList<String>();
            String string;
            while ((string = reader.readLine()) != null) {
                names.add(string);
            }

            String deleted = null;
            for (String i:names) {
                if (i.split(", ")[2].equals(email)
                        && i.split(", ")[3].equals(password)) {
                    deleted = i;
                }
            }
            names.remove(deleted);

            writer = new BufferedWriter(new FileWriter("C:\\Program Files\\task4.1\\task4\\src\\base.csv"));
            for (String x: names){
                writer.write(x);
                writer.newLine();}  // разрыв строки
            if (deleted == null){
                throw new WrongCredentialsException("Неверно введены данные.");
            }
            System.out.println("Аккаунт удален.");
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public boolean hasAccount(String email) throws IOException {
        boolean have = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Program Files\\task4.1\\task4\\src\\base.csv"));){
            String string;
            while ((string = reader.readLine()) != null){
                if (string.split(", ")[2].equals(email)){
                    have = true;
                }
            }
        }
        return have;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException, IOException, WrongCredentialsException {
        Person man = null;
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Program Files\\task4.1\\task4\\src\\base.csv"));){
            String string;
            while ((string = reader.readLine()) != null){
                if (string.split(", ")[2].equals(email) && string.split(", ")[3].equals(password)){
                    man = new Person(string.split(", ")[0], string.split(", ")[1]);
                    numbers.setCount(0);
                }
            }
            if (man == null){
                System.out.println(numbers.Counter());
                throw new WrongCredentialsException("Введены неверные данные!");
            }
        }
        return man;
    }


    @Override
    public int numOfAccounts() throws IOException {
        // подсчитываем количество аккаунтов
        int num = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Program Files\\task4.1\\task4\\src\\base.csv"));){
            while (reader.readLine() != null){
                num+=1;
            }
        }
        return num;
    }
}