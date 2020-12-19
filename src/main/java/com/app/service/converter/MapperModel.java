package com.app.service.converter;

import com.app.model.*;
import com.app.model.dto.*;
import com.app.model.dto.create.*;
import com.app.model.dto.get.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashSet;

@Service
public interface MapperModel {

    //--------------------------------------------------------------------
    //--------------------------- CATEGORY -------------------------------
    //--------------------------------------------------------------------
    static CategoryDto fromCategoryToCategoryDto(Category category) {
        return category == null ? null : CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    static Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return categoryDto == null ? null : Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .channels(new LinkedHashSet<>())
                .films(new LinkedHashSet<>())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- CHANNEL --------------------------------
    //--------------------------------------------------------------------
    static CreateChannelDto fromChannelToCreateChannelDto(Channel channel) {
        return channel == null ? null : CreateChannelDto.builder()
                .name(channel.getName())
                .description(channel.getDescription())
                .categoryId(channel.getCategory().getId())
                .build();
    }

    static GetChannelDto fromChannelToGetChannelDto(Channel channel) {
        return channel == null ? null : GetChannelDto.builder()
                .id(channel.getId())
                .name(channel.getName())
                .description(channel.getDescription())
                .category(fromCategoryToCategoryDto(channel.getCategory()))
                .build();
    }

    static Channel fromCreateChannelDtoToChannel(CreateChannelDto createChannelDto) {
        return createChannelDto == null ? null : Channel.builder()
                .name(createChannelDto.getName())
                .description(createChannelDto.getDescription())
                .category(Category.builder().id(createChannelDto.getCategoryId()).build())
                .packTvs(new LinkedHashSet<>())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- PACK TV --------------------------------
    //--------------------------------------------------------------------
    static CreatePackTvDto fromPackTvToCreatePackTvDto(PackTv packTv) {
        return packTv == null ? null : CreatePackTvDto.builder()
                .name(packTv.getName())
                .description(packTv.getDescription())
                .price(packTv.getPrice())
                .build();
    }

    static GetPackTvDto fromPackTvToGetPackTvDto(PackTv packTv) {
        return packTv == null ? null : GetPackTvDto.builder()
                .id(packTv.getId())
                .name(packTv.getName())
                .description(packTv.getDescription())
                .price(packTv.getPrice())
                .build();
    }

    static PackTv fromCreatePackTvDtoToPackTv(CreatePackTvDto createPackTvDto) {
        return createPackTvDto == null ? null : PackTv.builder()
                .name(createPackTvDto.getName())
                .description(createPackTvDto.getDescription())
                .price(createPackTvDto.getPrice())
                .channels(new LinkedHashSet<>())
                .packs(new LinkedHashSet<>())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- PACK INTERNET --------------------------
    //--------------------------------------------------------------------
    static CreatePackInternetDto fromPackInternetToCreatePackInternetDto(PackInternet packInternet) {
        return packInternet == null ? null : CreatePackInternetDto.builder()
                .name(packInternet.getName())
                .description(packInternet.getDescription())
                .speedDownload(packInternet.getSpeedDownload())
                .speedUpload(packInternet.getSpeedUpload())
                .typeInternet(packInternet.getTypeInternet())
                .price(packInternet.getPrice())
                .build();
    }

    static GetPackInternetDto fromPackInternetToGetPackInternetDto(PackInternet packInternet) {
        return packInternet == null ? null : GetPackInternetDto.builder()
                .id(packInternet.getId())
                .name(packInternet.getName())
                .description(packInternet.getDescription())
                .speedDownload(packInternet.getSpeedDownload())
                .speedUpload(packInternet.getSpeedUpload())
                .typeInternet(packInternet.getTypeInternet())
                .price(packInternet.getPrice())
                .build();
    }

    static PackInternet fromCreatePackInternetDtoToPackInternet(CreatePackInternetDto createPackInternetDto) {
        return createPackInternetDto == null ? null : PackInternet.builder()
                .name(createPackInternetDto.getName())
                .description(createPackInternetDto.getDescription())
                .speedDownload(createPackInternetDto.getSpeedDownload())
                .speedUpload(createPackInternetDto.getSpeedUpload())
                .typeInternet(createPackInternetDto.getTypeInternet())
                .price(createPackInternetDto.getPrice())
                .packs(new LinkedHashSet<>())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- PACK PHONE -----------------------------
    //--------------------------------------------------------------------
    static CreatePackPhoneDto fromPackPhoneToCreatePackPhoneDto(PackPhone packPhone) {
        return packPhone == null ? null : CreatePackPhoneDto.builder()
                .name(packPhone.getName())
                .description(packPhone.getDescription())
                .price(packPhone.getPrice())
                .limitedConversation(packPhone.getLimitedConversation())
                .limitedInternet(packPhone.getLimitedInternet())
                .build();
    }

    static GetPackPhoneDto fromPackPhoneToGetPackPhoneDto(PackPhone packPhone) {
        return packPhone == null ? null : GetPackPhoneDto.builder()
                .id(packPhone.getId())
                .name(packPhone.getName())
                .description(packPhone.getDescription())
                .price(packPhone.getPrice())
                .limitedConversation(packPhone.getLimitedConversation())
                .limitedInternet(packPhone.getLimitedInternet())
                .build();
    }

    static PackPhone fromCreatePackPhoneDtoToPackPhone(CreatePackPhoneDto createPackPhoneDto) {
        return createPackPhoneDto == null ? null : PackPhone.builder()
                .name(createPackPhoneDto.getName())
                .description(createPackPhoneDto.getDescription())
                .price(createPackPhoneDto.getPrice())
                .limitedConversation(createPackPhoneDto.getLimitedConversation())
                .limitedInternet(createPackPhoneDto.getLimitedInternet())
                .packs(new LinkedHashSet<>())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- PACK -----------------------------------
    //--------------------------------------------------------------------
    static CreatePackDto fromPackToCreatePackDto(Pack pack) {
        return pack == null ? null : CreatePackDto.builder()
                .name(pack.getName())
                .description(pack.getDescription())
                .discount(pack.getDiscount())
                .packPhoneId(pack.getPackPhone() == null ? null : pack.getPackPhone().getId())
                .packInternetId(pack.getPackInternet() == null ? null : pack.getPackInternet().getId())
                .packTvId(pack.getPackTv() == null ? null : pack.getPackTv().getId())
                .build();
    }

    static GetPackDto fromPackToGetPackDto(Pack pack) {
        return pack == null ? null : GetPackDto.builder()
                .id(pack.getId())
                .name(pack.getName())
                .description(pack.getDescription())
                .discount(pack.getDiscount())
                .packPhone(fromPackPhoneToGetPackPhoneDto(pack.getPackPhone()))
                .packInternet(fromPackInternetToGetPackInternetDto(pack.getPackInternet()))
                .packTv(fromPackTvToGetPackTvDto(pack.getPackTv()))
                .build();
    }

    static Pack fromCreatePackDtoToPack(CreatePackDto createPackDto) {
        return createPackDto == null ? null : Pack.builder()
                .name(createPackDto.getName())
                .description(createPackDto.getDescription())
                .discount(createPackDto.getDiscount())
                .packPhone(createPackDto.getPackPhoneId() == null ? null : PackPhone.builder().id(createPackDto.getPackPhoneId()).build())
                .packInternet(createPackDto.getPackInternetId() == null ? null : PackInternet.builder().id(createPackDto.getPackInternetId()).build())
                .packTv(createPackDto.getPackTvId() == null ? null : PackTv.builder().id(createPackDto.getPackTvId()).build())
                .build();
    }

    static GetPackDtoSimplif fromGetPackDtoToGetPackDtoSimplif(GetPackDto getPackDto) {
        return getPackDto == null ? null : GetPackDtoSimplif.builder()
                .id(getPackDto.getId())
                .name(getPackDto.getName())
                .description(getPackDto.getDescription())
                .price(BigDecimal.ZERO
                        .add(getPackDto.getPackPhone() == null ? BigDecimal.ZERO : getPackDto.getPackPhone().getPrice())
                        .add(getPackDto.getPackInternet() == null ? BigDecimal.ZERO : getPackDto.getPackInternet().getPrice())
                        .add(getPackDto.getPackTv() == null ? BigDecimal.ZERO : getPackDto.getPackTv().getPrice()))
                .discount(getPackDto.getDiscount())
                .namePackPhone(getPackDto.getPackPhone() == null ? null : getPackDto.getPackPhone().getName())
                .idPackPhone(getPackDto.getPackPhone() == null ? null : getPackDto.getPackPhone().getId())
                .namePackInternet(getPackDto.getPackInternet() == null ? null : getPackDto.getPackInternet().getName())
                .idPackInternet(getPackDto.getPackInternet() == null ? null : getPackDto.getPackInternet().getId())
                .namePackTv(getPackDto.getPackTv() == null ? null : getPackDto.getPackTv().getName())
                .idPackTv(getPackDto.getPackTv() == null ? null : getPackDto.getPackTv().getId())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- BUY PACK -------------------------------
    //--------------------------------------------------------------------
    static CreateBuyPackDto fromBuyPackToCreateBuyPackDto(BuyPack buyPack) {
        return buyPack == null ? null : CreateBuyPackDto.builder()
                .dateStart(buyPack.getDateStart())
                .dateEnd(buyPack.getDateEnd())
                .packId(buyPack.getPack().getId())
                .userId(buyPack.getUser().getId())
                .build();
    }

    static GetBuyPackDto fromBuyPackToGetBuyPackDto(BuyPack buyPack) {
        return buyPack == null ? null : GetBuyPackDto.builder()
                .id(buyPack.getId())
                .dateStart(buyPack.getDateStart())
                .dateEnd(buyPack.getDateEnd())
                .pack(fromPackToGetPackDto(buyPack.getPack()))
                .user(fromUserToGetUserDto(buyPack.getUser()))
                .build();
    }

    static BuyPack fromBuyPackDtoToBuyPack(CreateBuyPackDto createBuyPackDto) {
        return createBuyPackDto == null ? null : BuyPack.builder()
                .dateStart(createBuyPackDto.getDateStart())
                .dateEnd(createBuyPackDto.getDateEnd())
                .pack(Pack.builder().id(createBuyPackDto.getPackId()).build())
                .user(User.builder().id(createBuyPackDto.getUserId()).build())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- USER -----------------------------------
    //--------------------------------------------------------------------
    static GetUserDto fromUserToGetUserDto(User user) {
        return user == null ? null : GetUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    static GetUserAdminDto fromUserToGetUserAdminDto(User user) {
        return user == null ? null : GetUserAdminDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    static GetUserUserDto fromUserToGetUserUserDto(User user) {
        return user == null ? null : GetUserUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .enabled(user.getEnabled() == true ? "Tak" : "Nie")
                .build();
    }

    static User fromCreateUserDtoToUser(CreateUserDto createUserDto) {
        return createUserDto == null ? null : User.builder()
                .username(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                .email(createUserDto.getEmail())
                .enabled(createUserDto.getEnabled())
                .role(createUserDto.getRole())
                .buyFilms(new LinkedHashSet<>())
                .buyPacks(new LinkedHashSet<>())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- BUY FILM -------------------------------
    //--------------------------------------------------------------------
    static CreateBuyFilmDto fromBuyFilmToCreateBuyFilmDto(BuyFilm buyFilm) {
        return buyFilm == null ? null : CreateBuyFilmDto.builder()
                .dateStart(buyFilm.getDateStart())
                .dateEnd(buyFilm.getDateEnd())
                .filmId(buyFilm.getFilm().getId())
                .userId(buyFilm.getUser().getId())
                .build();
    }

    static GetBuyFilmDto fromBuyFilmToGetBuyFilmDto(BuyFilm buyFilm) {
        return buyFilm == null ? null : GetBuyFilmDto.builder()
                .id(buyFilm.getId())
                .dateStart(buyFilm.getDateStart())
                .dateEnd(buyFilm.getDateEnd())
                .film(fromFilmToGetFilmDto(buyFilm.getFilm()))
                .user(fromUserToGetUserDto(buyFilm.getUser()))
                .build();
    }

    static BuyFilm fromBuyFilmDtoToBuyFilm(CreateBuyFilmDto createBuyFilmDto) {
        return createBuyFilmDto == null ? null : BuyFilm.builder()
                .dateStart(createBuyFilmDto.getDateStart())
                .dateEnd(createBuyFilmDto.getDateEnd())
                .film(Film.builder().id(createBuyFilmDto.getFilmId()).build())
                .user(User.builder().id(createBuyFilmDto.getUserId()).build())
                .build();
    }

    //--------------------------------------------------------------------
    //--------------------------- FILM -----------------------------------
    //--------------------------------------------------------------------
    static CreateFilmDto fromFilmToCreateFilmDto(Film film) {
        return film == null ? null : CreateFilmDto.builder()
                .title(film.getTitle())
                .yearProduction(film.getYearProduction())
                .description(film.getDescription())
                .price(film.getPrice())
                .quality(film.getQuality())
                .categoryId(film.getCategory().getId())
                .build();
    }

    static GetFilmDto fromFilmToGetFilmDto(Film film) {
        return film == null ? null : GetFilmDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .yearProduction(film.getYearProduction())
                .description(film.getDescription())
                .price(film.getPrice())
                .quality(film.getQuality())
                .category(fromCategoryToCategoryDto(film.getCategory()))
                .build();
    }

    static Film fromCreateFilmDtoToFilm(CreateFilmDto createFilmDto) {
        return createFilmDto == null ? null : Film.builder()
                .title(createFilmDto.getTitle())
                .yearProduction(createFilmDto.getYearProduction())
                .description(createFilmDto.getDescription())
                .price(createFilmDto.getPrice())
                .quality(createFilmDto.getQuality())
                .category(Category.builder().id(createFilmDto.getCategoryId()).build())
                .buyFilms(new LinkedHashSet<>())
                .build();
    }
}
