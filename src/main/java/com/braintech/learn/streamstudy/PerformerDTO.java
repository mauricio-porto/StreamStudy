package com.braintech.learn.streamstudy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformerDTO {
    private String name;
    private String criteria;
    private Double rate;
    private String month;
}
