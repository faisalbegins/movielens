package edu.miu.utils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class StreamConfig {
    private static final String FILE_NAME = "stream_config";

    private static boolean isParallel = false;

    static {
        Optional<String> option = FileContentReader.readFileContent(FILE_NAME);
        option.ifPresent(s -> isParallel = Boolean.parseBoolean(s));
    }

    static public <T> Stream<T> streamOf(List<T> list) {
        return isParallel ? list.parallelStream() : list.stream();
    }

    static public <T> Stream<T> streamOf(Set<T> set) {
        return isParallel ? set.parallelStream() : set.stream();
    }
}
