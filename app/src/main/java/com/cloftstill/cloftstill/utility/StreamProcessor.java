package com.cloftstill.cloftstill.utility;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Fornece métodos para a extração de Strings a partir de Streams.
 */
public class StreamProcessor {

    /**
     * Recupera a mensagem armazenada no BufferedReader.
     *
     * @param reader BufferedReader a ser lido.
     * @return Mensagem armazenada no BufferedReader.
     * @throws IOException Caso o Reader seja fechado ou algum outro problema ocorra.
     */
    public static String getMessageFromReader(BufferedReader reader) throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

}
