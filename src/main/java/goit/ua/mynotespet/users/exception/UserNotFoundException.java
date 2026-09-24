package goit.ua.mynotespet.users.exception;

import goit.ua.mynotespet.error.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
