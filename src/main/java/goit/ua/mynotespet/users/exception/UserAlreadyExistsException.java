package goit.ua.mynotespet.users.exception;

import goit.ua.mynotespet.error.AlreadyExistsException;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
