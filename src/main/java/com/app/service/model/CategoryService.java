package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Category;
import com.app.model.dto.CategoryDto;
import com.app.model.dto.get.GetChannelDto;
import com.app.model.dto.get.GetFilmDto;
import com.app.repository.CategoryRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ChannelService channelService;
    private final FilmService filmService;

    public CategoryDto addOrUpdateCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE CATEGORY - CATEGORY IS NULL");
        }
        Category category = MapperModel.fromCategoryDtoToCategory(categoryDto);
        return MapperModel.fromCategoryToCategoryDto(categoryRepository.save(category));
    }

    public CategoryDto deleteCategoryById(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE CATEGORY - ID IS NULL");
        }
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NullPointerException("NOT FOUND CATEGORY"));
        categoryRepository.delete(category);
        return MapperModel.fromCategoryToCategoryDto(category);
    }

    public CategoryDto deleteCategoryByName(String name) {
        if (name == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE CATEGORY - NAME IS NULL");
        }
        Category category = categoryRepository.findByName(name).orElseThrow(() -> new NullPointerException("NOT FOUND CATEGORY"));
        categoryRepository.delete(category);
        return MapperModel.fromCategoryToCategoryDto(category);
    }

    public List<CategoryDto> getAllCategory() {
        return categoryRepository
                .findAll()
                .stream()
                .map(MapperModel::fromCategoryToCategoryDto)
                .collect(Collectors.toList());
    }

    public Map<CategoryDto, Long> countCategories() {

        Map<CategoryDto, Long> mapCategoryChannel = channelService.getAllChannel()
                .stream()
                .map(GetChannelDto::getCategory)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<CategoryDto, Long> mapCategoryFilm = filmService.getAllFilm()
                .stream()
                .map(GetFilmDto::getCategory)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<CategoryDto, Long> result = new HashMap<>();

        for (Map.Entry<CategoryDto, Long> film : mapCategoryFilm.entrySet()) {
            for (Map.Entry<CategoryDto, Long> channel : mapCategoryChannel.entrySet()) {
                if (film.getKey().getId() == channel.getKey().getId()) {
                    result.put(film.getKey(), film.getValue() + channel.getValue());
                    mapCategoryFilm.remove(film.getKey(), film.getValue());
                    mapCategoryChannel.remove(film.getKey(), film.getValue());
                    break;
                }
            }
        }
        for (Map.Entry<CategoryDto, Long> film : mapCategoryFilm.entrySet()) {
            result.put(film.getKey(), film.getValue());
        }
        for (Map.Entry<CategoryDto, Long> channel : mapCategoryChannel.entrySet()) {
            result.put(channel.getKey(), channel.getValue());
        }

        return result;
    }
}
