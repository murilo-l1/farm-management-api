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
    public static final String INVALID_NAME_LENGTH = "Tamanho de nome inválido";
    public static final String REQUIRED_EMAIL = "O email é obrigatório";
    public static final String INVALID_EMAIL = "Email inválido";
    public static final String REQUIRED_PASSWORD = "A senha é obrigatória";
    public static final String INVALID_PASSWORD_LENGTH = "A senha deve ter entre 8 e 100 caracteres";
    public static final String REQUIRED_PHONE = "O telefone é obrigatório";
    public static final String INVALID_PHONE_LENGTH = "O telefone deve ter entre 10 e 11 caracteres";
    public static final String POSITIVE_OR_ZERO = "O valor deve ser maior ou igual à zero";

    // Category
    public static final String CATEGORY_NOT_FOUND = "Categoria não encontrada";

    // Item
    public static final String ITEM_NOT_FOUND = "Item não encontrado";
    public static final String INVALID_UNITY_LENGTH = "A unidade deve ter no máximo 50 caracteres";
    public static final String INVALID_BRAND_LENGTH = "A marca deve ter no máximo 255 caracteres";

    // CropCycle
    public static final String CROP_CYCLE_NOT_FOUND = "Ciclo de cultura não encontrado";
    public static final String REQUIRED_CROP = "O tipo de cultura é obrigatório";
    public static final String INVALID_CROP_LENGTH = "A cultura deve ter no máximo 50 caracteres";
    public static final String INVALID_UNIT_LENGTH = "A unidade de medida deve ter no máximo 15 caracteres";
    public static final String REQUIRED_STATUS = "O status é obrigatório";
    public static final String REQUIRED_START_DATE = "A data de início é obrigatória";
    public static final String INVALID_MEASUREMENT_UNIT_PAIR = "Se a unidade é pés, o número de plantas deve ser informado";

    // Stakeholder
    public static final String STAKEHOLDER_NOT_FOUND = "Fornecedor/comprador não encontrado";
    public static final String INVALID_CPF = "CPF inválido";
    public static final String INVALID_CNPJ = "CNPJ inválido";
    public static final String INVALID_DOCUMENT_PAIR = "Não é possível informar CPF e CNPJ simultaneamente";
    public static final String INVALID_PHONE_STAKEHOLDER_LENGTH = "O telefone deve ter no máximo 20 caracteres";

}
