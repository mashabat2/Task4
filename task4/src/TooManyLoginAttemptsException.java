class TooManyLoginAttemptsException extends Exception {
    TooManyLoginAttemptsException(String sms){
        System.out.println(sms);
    }
}