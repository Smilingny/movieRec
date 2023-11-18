package com.example.movierec.dto;

import lombok.Data;

@Data
public class MovieSimple {
    private Integer id;
    private String title;
    private Integer duration;
    private Float rating;
    private String poster;
}
