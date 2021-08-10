package com.braintech.learn.streamstudy;

import lombok.Getter;

import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * {@link <a href="https://www.baeldung.com/java-break-stream-foreach">Source</a>}
 *
 */
public class CustomForEach {

    @Getter
    public static class Breaker {
        private boolean stopped = false;

        public void stop() {
            stopped = true;
        }
    }

    public static <T> void forEach(Stream<T> stream, BiConsumer<T, Breaker> consumer) {
        Spliterator<T> spliterator = stream.spliterator();
        boolean hasNext = true;
        Breaker breaker = new Breaker();

        while (hasNext && !breaker.isStopped()) {
            hasNext = spliterator.tryAdvance(elem -> {
                consumer.accept(elem, breaker);
            });
        }
    }
}
