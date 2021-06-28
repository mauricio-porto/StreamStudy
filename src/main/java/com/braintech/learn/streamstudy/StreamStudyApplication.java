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

@Slf4j
@SpringBootApplication
public class StreamStudyApplication implements CommandLineRunner {

    private final Map<String, String> criteriaByMonth = Map.of(
            "Jan", "speed",
            "Mar", "size",
            "Feb", "koisa",
            "Jun", "cost");

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
        SpringApplication.run(StreamStudyApplication.class, args);
    }

    /**
     * A ideia aqui é usar o mapeamento criteriaByMonth para montar uma lista 'winners' agrupados por mês e um critério
     */
    @Override
    public void run(String... args) {
        Map<String, List<PerformerDTO>> winners = criteriaByMonth.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        data -> {
                            return someData
                                    .stream()
                                    .filter(some -> some.getMonth().equalsIgnoreCase(data.getKey()))
                                    .filter(some -> some.getCriteria().equalsIgnoreCase(data.getValue()))
                                    .collect(Collectors.toList());
                        }
                ));
        winners.forEach((k, v) -> {
            System.out.print(k);
            System.out.println(": " + v);
        });
    }
}
