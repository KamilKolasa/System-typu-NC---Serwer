package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.PackPhone;
import com.app.model.dto.create.CreatePackPhoneDto;
import com.app.model.dto.get.GetPackPhoneDto;
import com.app.repository.PackPhoneRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackPhoneService {

    private final PackPhoneRepository packPhoneRepository;

    public CreatePackPhoneDto addOrUpdatePackPhone(CreatePackPhoneDto createPackPhoneDto) {
        if (createPackPhoneDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE PACK_PHONE - ACK_PHONE IS NULL");
        }
        PackPhone packPhone = MapperModel.fromCreatePackPhoneDtoToPackPhone(createPackPhoneDto);
        return MapperModel.fromPackPhoneToCreatePackPhoneDto(packPhoneRepository.save(packPhone));
    }

    public GetPackPhoneDto deletePackPhone(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE PACK_PHONE - ID IS NULL");
        }
        PackPhone packPhone = packPhoneRepository.getOne(id);
        packPhoneRepository.delete(packPhone);
        return MapperModel.fromPackPhoneToGetPackPhoneDto(packPhone);
    }

    public List<GetPackPhoneDto> getAllPackPhone() {
        return packPhoneRepository
                .findAll()
                .stream()
                .map(MapperModel::fromPackPhoneToGetPackPhoneDto)
                .collect(Collectors.toList());
    }
}