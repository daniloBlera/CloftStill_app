package com.cloftstill.cloftstill.model;

/**
 * Agrupa todas as possíveis respostas do servidor.
 */
public enum ServerResponse {
    /**
     * Quando o servidor pegou uma exceção ao tentar acessar uma key que não existia no Json, é
     * provável que o JSON tenha sido mal formado.
     */
    JSON_MISSING_KEYS,
    /**
     * Quando o servidor tentou colocar no banco de dados um valor inválido, e.g.: String no lugar
     * de Integer ou Null onde o está marcado como 'NOT NULL'.
     */
    JSON_MISSING_ARGS,
    /**
     * Dispositivo tentando enviar novamente solicitações ao banco, i.e., já existe uma solicitação
     * cadastrada no banco com o endereço MAC e código serial do SIM do dispositivo.
     */
    ALREADY_ACCEPTED,
    /**
     * Solicitação de cadastro recebida pelo servidor com sucesso, aguardando aprovação.
     */
    SOLICITATION_ACCEPTED,
    /**
     * Mensagem de erro genérica caso alguma exceção/erro tenha ocorrido no banco, não tem muito o
     * que fazer do lado do app senão imprimir um "Erro interno no servidor, desculpe o incômodo",
     * do lado do servidor sempre que isso ocorrer a exceção vai ser salva no log de erros.
     */
    INTERNAL_ERROR,
    /**
     * Os dados recebidos existem no banco de dados mas a senha difere com o valor no banco.
     */
    INCORRECT_PASSWORD,
    /**
     * Caso o usuário seja um ADMIN.
     */
    ADMIN_VERIFICATION_TRUE,
    /**
     * Caso o usuário NÃO seja um ADMIN.
     */
    ADMIN_VERIFICATION_FALSE,
    /**
     * Clift-Cloft-Still, a porta se abriu (assim espero).
     */
    REQUEST_SENT,
    /**
     * O usuário não possui autorização para abrir a porta.
     */
    REQUEST_DENIED,
    /**
     * O dispositivo não está cadastrado no banco de dados, possíveis motivos:
     * 1 - O usuário abriu o app e já foi clicando em Abrir porta sem ter solicitado o cadastro;
     * 2 - O usuário clicou em Abrir Porta mas a sua solicitação ainda não foi autorizada;
     */
    UNREGISTERED_USER;

    @Override
    public String toString() {
        switch (this) {
            case JSON_MISSING_ARGS:
                return "JSON_INVALID_ARGS";
            case JSON_MISSING_KEYS:
                return "JSON_MISSING_KEYS";
            case ALREADY_ACCEPTED:
                return "SOLICITATION_ALREADY_ACCEPTED";
            case SOLICITATION_ACCEPTED:
                return "SOLICITATION_ACCEPTED";
            case INTERNAL_ERROR:
                return "INTERNAL_SERVER_ERROR";
            case REQUEST_SENT:
                return "REQUEST_SENT";
            case REQUEST_DENIED:
                return "REQUEST_DENIED";
            case ADMIN_VERIFICATION_TRUE:
                return "ADMIN_VALIDITY_CONFIRMED";
            case ADMIN_VERIFICATION_FALSE:
                return "USER_IS_NOT_ADMIN";
            case INCORRECT_PASSWORD:
                return "INCORRECT_PASSWORD";
            case UNREGISTERED_USER:
                return "UNREGISTERED_USER";
            default:
                return "UNDEFINED";
        }
    }
}
