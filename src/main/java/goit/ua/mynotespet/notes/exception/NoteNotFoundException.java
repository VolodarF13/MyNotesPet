package goit.ua.mynotespet.notes.exception;

import goit.ua.mynotespet.error.NotFoundException;

public class NoteNotFoundException extends NotFoundException {
    public NoteNotFoundException(String message) {
        super(message);
    }
}
