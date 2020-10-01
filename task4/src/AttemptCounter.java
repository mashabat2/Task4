public class AttemptCounter {
    private static AttemptCounter instance;

    // счетчик попыток входа в почтовый сервер
    private int count = 0;

    // геттер
    public int getCount(){
        return count;
    }

    // сеттер
    public void setCount(int count){
        this.count = count;
    }

    public static synchronized AttemptCounter getInstance(){
        if (instance == null){
            instance = new AttemptCounter();
        }
        return instance;
    }


    public int Counter()
            throws TooManyLoginAttemptsException {
        count += 1;
        if (count > 5){
            throw new TooManyLoginAttemptsException("Превышено количество попыток входа!");
        }
        return count;
    }
}