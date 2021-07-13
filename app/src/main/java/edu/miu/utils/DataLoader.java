package edu.miu.utils;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

public class DataLoader {

    private DataLoader() {
        initialize();
    }

    private static class Holder {
        private static final DataLoader INSTANCE = new DataLoader();
    }

    public static DataLoader getInstance() {
        return Holder.INSTANCE;
    }

    private void initialize() {
        Optional<String> result = readFileContent();
        result.ifPresent(System.out::println);
    }

    private Optional<String> readFileContent() {
        InputStream stream = DataLoader.class.getResourceAsStream("/data.csv");
        if(stream == null) return Optional.empty();
        String text = null;
        try (Reader reader = new InputStreamReader(stream)) {
            text = CharStreams.toString(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(text);
    }
}
