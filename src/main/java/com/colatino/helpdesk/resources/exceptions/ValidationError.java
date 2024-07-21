package com.colatino.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    //Deixou de ser SetErrors e virou addErrors
    public void addErrors(String fieldName, String message) { //Deixou de receber List<FieldMessage> e passou a receber nome do Campo e amensagem
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
