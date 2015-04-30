package com.IMaylatov.Recommend.webapp.Service.Register;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 30.04.2015
 */
public class RegisterUser {
    private boolean success;
    private String message;

    public RegisterUser(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
