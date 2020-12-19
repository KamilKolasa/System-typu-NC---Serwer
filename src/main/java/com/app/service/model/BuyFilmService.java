package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.BuyFilm;
import com.app.model.dto.create.CreateBuyFilmDto;
import com.app.model.dto.get.GetBuyFilmDto;
import com.app.repository.BuyFilmRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyFilmService {

    private final BuyFilmRepository buyFilmRepository;

    public CreateBuyFilmDto addOrUpdateBuyFilm(CreateBuyFilmDto createBuyFilmDto) throws MyException {
        if (createBuyFilmDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE BUY_FILM - BUY_FILM IS NULL");
        }
        BuyFilm buyFilm = MapperModel.fromBuyFilmDtoToBuyFilm(createBuyFilmDto);
        return MapperModel.fromBuyFilmToCreateBuyFilmDto(buyFilmRepository.save(buyFilm));
    }

    public GetBuyFilmDto deleteBuyFilm(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE BUY_FILM - ID IS NULL");
        }
        BuyFilm buyFilm = buyFilmRepository.getOne(id);
        buyFilmRepository.delete(buyFilm);
        return MapperModel.fromBuyFilmToGetBuyFilmDto(buyFilm);
    }
}