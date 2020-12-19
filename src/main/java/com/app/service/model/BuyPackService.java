package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.BuyPack;
import com.app.model.dto.create.CreateBuyPackDto;
import com.app.model.dto.get.GetBuyPackDto;
import com.app.repository.BuyPackRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyPackService {

    private final BuyPackRepository buyPackRepository;

    public CreateBuyPackDto addOrUpdateBuyPack(CreateBuyPackDto createBuyPackDto) throws MyException {
        if (createBuyPackDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE BUY_PACK - BUY_PACK IS NULL");
        }
        BuyPack buyPack = MapperModel.fromBuyPackDtoToBuyPack(createBuyPackDto);



        return MapperModel.fromBuyPackToCreateBuyPackDto(buyPackRepository.save(buyPack));
    }

    public GetBuyPackDto deleteBuyPack(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE BUY_PACK - ID IS NULL");
        }
        BuyPack buyPack = buyPackRepository.getOne(id);
        buyPackRepository.delete(buyPack);
        return MapperModel.fromBuyPackToGetBuyPackDto(buyPack);
    }
}