class DuplicateAccountException extends Exception {
    DuplicateAccountException(String sms){
        System.out.println(sms);
    }
}