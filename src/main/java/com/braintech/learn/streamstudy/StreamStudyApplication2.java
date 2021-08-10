package com.braintech.learn.streamstudy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class StreamStudyApplication2 implements CommandLineRunner {

    private final Map<String, String> criteriaByMonth = Map.of(
            "Jan", "speed",
            "Mar", "size",
            "Feb", "koisa",
            "Jun", "cost");

    private final String[] priority = {"I", "B", "E", "C"};

    private final List<PerformerDTO> someData = Arrays.asList(
            PerformerDTO.builder().name("A").criteria("speed").rate(1.5).month("Mar").build(),
            PerformerDTO.builder().name("B").criteria("size").rate(1.5).month("Mar").build(),
            PerformerDTO.builder().name("C").criteria("speed").rate(1.5).month("Jan").build(),
            PerformerDTO.builder().name("D").criteria("size").rate(1.5).month("Feb").build(),
            PerformerDTO.builder().name("E").criteria("speed").rate(1.5).month("Jan").build(),
            PerformerDTO.builder().name("F").criteria("size").rate(1.5).month("Mar").build(),
            PerformerDTO.builder().name("G").criteria("koisa").rate(1.5).month("Feb").build(),
            PerformerDTO.builder().name("H").criteria("cost").rate(1.5).month("Jun").build(),
            PerformerDTO.builder().name("I").criteria("size").rate(1.5).month("Mar").build(),
            PerformerDTO.builder().name("J").criteria("size").rate(1.5).month("Apr").build());

    public static void main(String[] args) {
        SpringApplication.run(StreamStudyApplication2.class, args);
    }

    /**
     * A ideia aqui é usar o mapeamento criteriaByMonth para montar uma lista 'winners' agrupados por mês e um critério
     */
    @Override
    public void run(String... args) {
//        for (String order: priority) {
//            someData.stream()
//                    .filter(performerDTO -> performerDTO.getName().equalsIgnoreCase(order))
//                    .findFirst()
//                    .ifPresent(performerDTO -> {
//                        System.out.println(performerDTO.getMonth());
//                    });
//        }

        Stream.of(priority)
                .takeWhile(n -> n.length() % 2 != 0)
                .forEach(System.out::println);

        Stream<String> initialStream = Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck");
        List<String> result = new ArrayList<>();

        CustomForEach.forEach(initialStream, (elem, breaker) -> {
            if (elem.length() % 2 == 0) {
                breaker.stop();
            } else {
                result.add(elem);
            }
        });
        result.forEach(System.out::println);
    }
}
