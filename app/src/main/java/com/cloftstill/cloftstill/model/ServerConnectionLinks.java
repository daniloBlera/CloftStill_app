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
    VALIDATE_USER;

    @Override
    public String toString() {
        switch (this) {
            case LOCALHOST_DOMAIN:
                return "10.0.3.2:5000";
            case OPEN_DOOR_PATH:
                return "porta/abre/";
            case REQUEST_SIGNUP:
                return "solicitacao/adiciona/";
            case VALIDATE_USER:
                return "pessoa/valida/";
            default:
                return "DEFAULT_OPTION";
        }
    }
}
