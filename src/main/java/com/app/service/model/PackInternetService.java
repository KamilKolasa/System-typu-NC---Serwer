package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.PackInternet;
import com.app.model.dto.create.CreatePackInternetDto;
import com.app.model.dto.get.GetPackInternetDto;
import com.app.repository.PackInternetRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackInternetService {

    private final PackInternetRepository packInternetRepository;

    public CreatePackInternetDto addOrUpdatePackInternet(CreatePackInternetDto createPackInternetDto) {
        if (createPackInternetDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE PACK_INTERNET - PACK_INTERNET IS NULL");
        }
        PackInternet packInternet = MapperModel.fromCreatePackInternetDtoToPackInternet(createPackInternetDto);
        return MapperModel.fromPackInternetToCreatePackInternetDto(packInternetRepository.save(packInternet));
    }

    public GetPackInternetDto deletePackInternet(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE PACK_INTERNET - ID IS NULL");
        }
        PackInternet packInternet = packInternetRepository.getOne(id);
        packInternetRepository.delete(packInternet);
        return MapperModel.fromPackInternetToGetPackInternetDto(packInternet);
    }

    public List<GetPackInternetDto> getAllPackInternet() {
        return packInternetRepository
                .findAll()
                .stream()
                .map(MapperModel::fromPackInternetToGetPackInternetDto)
                .collect(Collectors.toList());
    }
}