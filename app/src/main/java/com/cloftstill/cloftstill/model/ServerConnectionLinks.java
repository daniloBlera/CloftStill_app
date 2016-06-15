package com.cloftstill.cloftstill.model;

/**
 * Contém todos os links para o servidor web.
 *
 * Exemplo de uso:
 *
 * String url = String.format("http://%s/%s",
 *         ServerConnectionLinks.Domain.toString(),
 *         ServerConnectionLinks.OPEN_DOOR_PATH.toString());
 *
 * Caso o emulador android esteja sendo executado no mesmo computador que esteja executando o
 * servidor web, o endereço 'localhost' deste pc caso o emulador seja o Genymotion é '10.0.3.2'
 */
public enum ServerConnectionLinks {
    LOCALHOST_DOMAIN,
    OPEN_DOOR_PATH,
    REQUEST_SIGNUP,
    CHECK_IF_ADMIN,
    GET_ALL_USERS,
    APPROVE_SOLICITATION,
    GET_ALL_PENDING_SOLICITATIONS;

    @Override
    public String toString() {
        switch (this) {
            case LOCALHOST_DOMAIN:
                return "10.0.3.2:5000";
            case OPEN_DOOR_PATH:
                return "porta/abre/";
            case REQUEST_SIGNUP:
                return "solicitacao/adiciona/";
            case CHECK_IF_ADMIN:
                return "pessoa/valida/";
            case GET_ALL_USERS:
                return "pessoa/cadastrados/";
            case APPROVE_SOLICITATION:
                return "solicitacao/aprova/";
            case GET_ALL_PENDING_SOLICITATIONS:
                return "solicitacao/aguardando/";
            default:
                return "DEFAULT_OPTION";
        }
    }
}
