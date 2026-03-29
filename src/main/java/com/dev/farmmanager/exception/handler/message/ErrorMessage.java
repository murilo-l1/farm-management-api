package com.dev.farmmanager.exception.handler.message;

public final class ErrorMessage {
    // TODO: pensar em mecanismo de i18n, mas pro projeto vai ser só português

    private ErrorMessage(){}

    public static final String USER_NOT_FOUND = "Usuário não encontrado";
    public static final String BLANK_CREDENTIALS = "Credenciais em branco";
    public static final String INVALID_CREDENTIALS = "Email ou senha incorretos";
    public static final String ALREADY_EXISTS = "Email já cadastrado";
    
    // Payload Validation messages
    public static final String REQUIRED_NAME = "O nome é obrigatório";
    public static final String INVALID_NAME_LENGTH = "O nome deve ter entre 5 e 100 caracteres";
    public static final String REQUIRED_EMAIL = "O email é obrigatório";
    public static final String INVALID_EMAIL = "Email inválido";
    public static final String REQUIRED_PASSWORD = "A senha é obrigatória";
    public static final String INVALID_PASSWORD_LENGTH = "A senha deve ter entre 8 e 100 caracteres";
    public static final String REQUIRED_PHONE = "O telefone é obrigatório";
    public static final String INVALID_PHONE_LENGTH = "O telefone deve ter entre 10 e 11 caracteres";


}