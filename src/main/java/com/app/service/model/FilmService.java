package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Category;
import com.app.model.Film;
import com.app.model.dto.create.CreateFilmDto;
import com.app.model.dto.get.GetFilmDto;
import com.app.repository.CategoryRepository;
import com.app.repository.FilmRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;

    public CreateFilmDto addOrUpdateFilm(CreateFilmDto createFilmDto) {
        if (createFilmDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE FILM - FILM IS NULL");
        }
        if (createFilmDto.getCategoryId() == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE FILM - FILM WITHOUT CATEGORY ID");
        }
        Film film = MapperModel.fromCreateFilmDtoToFilm(createFilmDto);

        Category category = categoryRepository.findById(createFilmDto.getCategoryId()).orElseThrow(() -> new NullPointerException("NOT FOUND CATEGORY"));
        film.setCategory(category);

        return MapperModel.fromFilmToCreateFilmDto(filmRepository.save(film));
    }

    public GetFilmDto deleteFilm(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE FILM - ID IS NULL");
        }
        Film film = filmRepository.getOne(id);
        filmRepository.delete(film);
        return MapperModel.fromFilmToGetFilmDto(film);
    }

    public List<GetFilmDto> getAllFilm() {
        return filmRepository
                .findAll()
                .stream()
                .map(MapperModel::fromFilmToGetFilmDto)
                .collect(Collectors.toList());
    }
}