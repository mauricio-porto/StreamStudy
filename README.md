# Estudos sobre Stream

## Filtrando e agrupando por algum critério

O primeiro estudo utiliza um _Map<String, String>_ (chamado **_criteriaByMonth_**) para definir critérios de filtragem e agrupamento para um conjunto de dados qualquer.

Esse **_criteriaByMonth_** tem como chave a sigla do mês e como valor o critério de filtragem dos dados para aquele mês.

```text
Map<String, String> criteriaByMonth = Map.of(
            "Jan", "speed",
            "Mar", "size",
            "Feb", "koisa",
            "Jun", "cost");
```

A massa de dados a ser filtrada e agrupada poderia ser qualquer coisa, como um mapeamento de uma consulta em banco de dados ou um conjunto de registros de log, por exemplo.

Para manter o estudo conciso e mais fácil de entender, usamos uma declaração local de dados (chamada **_someData_**) composta de uma lista de **_PerformerDTO_** (um DTO que representa dados de performance de um recurso qualquer).

```text
List<PerformerDTO> someData = Arrays.asList(
    PerformerDTO.builder().name("A").criteria("speed").rate(1.5).month("Mar").build(),
    PerformerDTO.builder().name("B").criteria("size").rate(1.5).month("Mar").build(),
    PerformerDTO.builder().name("C").criteria("speed").rate(1.5).month("Jan").build(),
    PerformerDTO.builder().name("D").criteria("size").rate(1.5).month("Feb").build(),
    ...
    ...
```

O código que faz a filtragem e agrupamento da massa de dados pelos critérios declarados por **_criteriaByMonth_** ficou assim:

```text
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
```

Para executar o trabalho, obtemos o conjunto todo do **_criteriaByMonth_** através do método **_entrySet(_**), 
o convertemos para um _stream_ e em seguida coletamos (método _collect_) um novo _Map_ que possui como chave 
a mesma chave (o mês) que o **_criteriaByMonth_** e como valor (_data_ no código) uma lista de **_PerformerDTO_** que foi adequadamente filtrada (mês e critério).

O resultado da execução é:

```text
Jun: [PerformerDTO(name=H, criteria=cost, rate=1.5, month=Jun)]
Feb: [PerformerDTO(name=G, criteria=koisa, rate=1.5, month=Feb)]
Jan: [PerformerDTO(name=C, criteria=speed, rate=1.5, month=Jan), PerformerDTO(name=E, criteria=speed, rate=1.5, month=Jan)]
Mar: [PerformerDTO(name=B, criteria=size, rate=1.5, month=Mar), PerformerDTO(name=F, criteria=size, rate=1.5, month=Mar), PerformerDTO(name=I, criteria=size, rate=1.5, month=Mar)]
```
