package com.cloftstill.cloftstill.utility;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by dcblera on 30/05/16.
 */
public class StreamProcessor {

    /**
     * Recupera a mensagem armazenada no BufferedReader.
     *
     * @param reader BufferedReader a ser lido.
     * @return Mensagem armazenada no BufferedReader.
     * @throws IOException
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
