package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Pack;
import com.app.model.PackInternet;
import com.app.model.PackPhone;
import com.app.model.PackTv;
import com.app.model.dto.create.CreatePackDto;
import com.app.model.dto.get.GetPackDto;
import com.app.model.dto.get.GetPackDtoSimplif;
import com.app.repository.PackInternetRepository;
import com.app.repository.PackPhoneRepository;
import com.app.repository.PackRepository;
import com.app.repository.PackTvRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackService {

    private final PackRepository packRepository;
    private final PackTvRepository packTvRepository;
    private final PackInternetRepository packInternetRepository;
    private final PackPhoneRepository packPhoneRepository;

    public CreatePackDto addOrUpdatePack(CreatePackDto createPackDto) {
        if (createPackDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE PACK - PACK IS NULL");
        }
        if (createPackDto.getPackPhoneId() == null && createPackDto.getPackInternetId() == null) {
            createPackDto.setName(packTvRepository.findById(createPackDto.getPackTvId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK TV")).getName());
            createPackDto.setDescription(packTvRepository.findById(createPackDto.getPackTvId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK TV")).getDescription());
        } else if (createPackDto.getPackInternetId() == null && createPackDto.getPackTvId() == null) {
            createPackDto.setName(packPhoneRepository.findById(createPackDto.getPackPhoneId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK PHONE")).getName());
            createPackDto.setDescription(packPhoneRepository.findById(createPackDto.getPackPhoneId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK PHONE")).getDescription());
        } else if (createPackDto.getPackTvId() == null && createPackDto.getPackPhoneId() == null) {
            createPackDto.setName(packInternetRepository.findById(createPackDto.getPackInternetId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK INTERNET")).getName());
            createPackDto.setDescription(packInternetRepository.findById(createPackDto.getPackInternetId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK INTERNET")).getDescription());
        }

        Pack pack = MapperModel.fromCreatePackDtoToPack(createPackDto);

        PackTv packTv = null;
        if (createPackDto.getPackTvId() != null)
            packTv = packTvRepository.findById(createPackDto.getPackTvId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK TV"));

        PackInternet packInternet = null;
        if (createPackDto.getPackInternetId() != null)
            packInternet = packInternetRepository.findById(createPackDto.getPackInternetId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK INTERNET"));

        PackPhone packPhone = null;
        if (createPackDto.getPackPhoneId() != null)
            packPhone = packPhoneRepository.findById(createPackDto.getPackPhoneId()).orElseThrow(() -> new NullPointerException("NOT FOUND PACK PHONE"));

        pack.setPackTv(packTv);
        pack.setPackInternet(packInternet);
        pack.setPackPhone(packPhone);

        return MapperModel.fromPackToCreatePackDto(packRepository.save(pack));
    }

    public GetPackDto deletePack(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE PACK - ID IS NULL");
        }
        Pack pack = packRepository.getOne(id);
        packRepository.delete(pack);
        return MapperModel.fromPackToGetPackDto(pack);
    }

    public List<GetPackDto> getAllPack() {
        return packRepository
                .findAll()
                .stream()
                .map(MapperModel::fromPackToGetPackDto)
                .collect(Collectors.toList());
    }

    public List<GetPackDtoSimplif> getAllPackSimplif() {
        return packRepository
                .findAll()
                .stream()
                .map(MapperModel::fromPackToGetPackDto)
                .map(MapperModel::fromGetPackDtoToGetPackDtoSimplif)
                .collect(Collectors.toList());
    }

    private Integer findDiscount() {
        return packRepository.findAllDiscount()
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .sorted()
                .findFirst()
                .orElse(0);
    }

    public List<GetPackDto> getBestThreePack() {
        List<GetPackDto> packs =
                packRepository.findAllPacksWithLargerDiscount(findDiscount())
                        .stream()
                        .map(MapperModel::fromPackToGetPackDto)
                        .collect(Collectors.toList());
        if (packs.size() == 3) {
            return packs;
        } else {
            return packs.stream().limit(3).collect(Collectors.toList());
        }
    }
}