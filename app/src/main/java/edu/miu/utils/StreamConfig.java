package edu.miu.utils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class StreamConfig {
    private static final String FILE_NAME = "stream_config";

    private static long threshold = 10000;

    static {
        Optional<String> option = FileContentReader.readFileContent(FILE_NAME);
        option.ifPresent(s -> threshold = Long.parseLong(s));
    }

    static public <T> Stream<T> streamOf(List<T> list) {
        return list.size() > threshold ? list.parallelStream() : list.stream();
    }

    static public <T> Stream<T> streamOf(Set<T> set) {
        return set.size() > threshold ? set.parallelStream() : set.stream();
    }
}
