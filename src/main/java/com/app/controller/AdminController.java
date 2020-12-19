package com.app.controller;

import com.app.model.dto.CategoryDto;
import com.app.model.dto.InfoDto;
import com.app.model.dto.create.CreateFilmDto;
import com.app.model.dto.get.*;
import com.app.service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final CategoryService categoryService;
    private final FilmService filmService;
    private final ChannelService channelService;
    private final PackTvService packTvService;
    private final PackInternetService packInternetService;
    private final PackPhoneService packPhoneService;
    private final PackService packService;
    private final UserService userService;

    @GetMapping("/countCategories")
    public InfoDto<Map<CategoryDto,Long>> countCategories() {
        var categorys = categoryService.countCategories();
        return InfoDto.<Map<CategoryDto,Long>>builder().data(categorys).build();
    }

    @GetMapping("/allCategorys")
    public InfoDto<List<CategoryDto>> getAllCategorys() {
        var categorys = categoryService.getAllCategory();
        return InfoDto.<List<CategoryDto>>builder().data(categorys).build();
    }

    @PostMapping("/addCategory")
    public InfoDto<String> postAddFilm(@RequestBody CategoryDto categoryDto) {
        var addCategory = categoryService.addOrUpdateCategory(categoryDto);
        return InfoDto.<String>builder().data("category " + addCategory.getName() + " add").build();
    }

    @GetMapping("/allFilms")
    public InfoDto<List<GetFilmDto>> getAllFilms() {
        var films = filmService.getAllFilm();
        return InfoDto.<List<GetFilmDto>>builder().data(films).build();
    }

    @PostMapping("/addFilm")
    public InfoDto<String> postAddFilm(@RequestBody CreateFilmDto createFilmDto) {
        var addFilm = filmService.addOrUpdateFilm(createFilmDto);
        return InfoDto.<String>builder().data("film " + addFilm.getTitle() + " add").build();
    }

    @GetMapping("/allChannels")
    public InfoDto<List<GetChannelDto>> getAllChannels() {
        var channels = channelService.getAllChannel();
        return InfoDto.<List<GetChannelDto>>builder().data(channels).build();
    }

    @GetMapping("/allPackTvs")
    public InfoDto<List<GetPackTvDto>> getAllPackTvs() {
        var packTvs = packTvService.getAllPackTv();
        return InfoDto.<List<GetPackTvDto>>builder().data(packTvs).build();
    }

    @GetMapping("/allPackInternets")
    public InfoDto<List<GetPackInternetDto>> getAllPackInternets() {
        var packInternets = packInternetService.getAllPackInternet();
        return InfoDto.<List<GetPackInternetDto>>builder().data(packInternets).build();
    }

    @GetMapping("/allPackPhones")
    public InfoDto<List<GetPackPhoneDto>> getAllPackPhones() {
        var packPhones = packPhoneService.getAllPackPhone();
        return InfoDto.<List<GetPackPhoneDto>>builder().data(packPhones).build();
    }

    @GetMapping("/allPacks")
    public InfoDto<List<GetPackDtoSimplif>> getAllPacks() {
        var packs = packService.getAllPackSimplif();
        return InfoDto.<List<GetPackDtoSimplif>>builder().data(packs).build();
    }

    @GetMapping("/allUsers")
    public InfoDto<List<GetUserUserDto>> getAllUsers() {
        var users = userService.getAllUser();
        return InfoDto.<List<GetUserUserDto>>builder().data(users).build();
    }

    @GetMapping("/allAdmins")
    public InfoDto<List<GetUserAdminDto>> getAllAdmins() {
        var admins = userService.getAllAdmin();
        return InfoDto.<List<GetUserAdminDto>>builder().data(admins).build();
    }
}
