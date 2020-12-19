package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.PackTv;
import com.app.model.dto.create.CreatePackTvDto;
import com.app.model.dto.get.GetPackTvDto;
import com.app.repository.PackTvRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackTvService {

    private final PackTvRepository packTvRepository;

    public CreatePackTvDto addOrUpdatePackTv(CreatePackTvDto createPackTvDto) {
        if (createPackTvDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE PACK_TV - PACK_TV IS NULL");
        }
        PackTv user = MapperModel.fromCreatePackTvDtoToPackTv(createPackTvDto);
        return MapperModel.fromPackTvToCreatePackTvDto(packTvRepository.save(user));
    }

    public GetPackTvDto deletePackTv(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE PACK_TV - ID IS NULL");
        }
        PackTv packTv = packTvRepository.getOne(id);
        packTvRepository.delete(packTv);
        return MapperModel.fromPackTvToGetPackTvDto(packTv);
    }

    public List<GetPackTvDto> getAllPackTv() {
        return packTvRepository
                .findAll()
                .stream()
                .map(MapperModel::fromPackTvToGetPackTvDto)
                .collect(Collectors.toList());
    }
}
