package com.dev.farmmanager.exception.handler;

public class UsernameNotAllowedException extends ForbiddenException {
    public UsernameNotAllowedException() {
        super("Nome de usuário não permitido");
    }
}
