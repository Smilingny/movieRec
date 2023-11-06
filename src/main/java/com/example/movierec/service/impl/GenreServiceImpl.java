package com.example.movierec.service.impl;

import com.example.movierec.mapper.GenreMapper;
import com.example.movierec.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreMapper genreMapper;
}
