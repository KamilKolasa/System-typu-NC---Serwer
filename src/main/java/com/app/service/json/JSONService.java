package com.app.service.json;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.BuyFilm;
import com.app.model.Category;
import com.app.model.dto.create.*;
import com.app.model.enums.Quality;
import com.app.model.enums.Role;
import com.app.model.dto.*;
import com.app.model.enums.TypeInternet;
import com.app.repository.*;
import com.app.service.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JSONService {

    @Value("${file.path}")
    private String filePath;
    private final CategoryRepository categoryRepository;
    private final FilmRepository filmRepository;
    private final ChannelRepository channelRepository;
    private final PackTvRepository packTvRepository;
    private final PackInternetRepository packInternetRepository;
    private final PackPhoneRepository packPhoneRepository;
    private final PackRepository packRepository;
    private final UserRepository userRepository;
//    private final BuyFilmRepository buyFilmRepository;
//    private final BuyPackRepository buyPackRepository;

    private final CategoryService categoryService;
    private final FilmService filmService;
    private final ChannelService channelService;
    private final PackTvService packTvService;
    private final PackInternetService packInternetService;
    private final PackPhoneService packPhoneService;
    private final PackService packService;
    private final UserService userService;
//    private final BuyFilmService buyFilmService;
//    private final BuyPackService buyPackService;

    private static String nameFileFilm = "fileFilm.json";
    private static String nameFileChannel = "fileChannel.json";
    private static String nameFileTvPack = "filePackTv.json";
    private static String nameFileInternetPack = "filePackInternet.json";
    private static String nameFilePhonePack = "filePackPhone.json";
    private static String nameFilePack = "filePack.json";
    private static String nameFileUser = "fileUser.json";
//    private static String nameFileBuyFilm = "fileBuyFilm.json";
//    private static String nameFileBuyPack = "fileBuyPack.json";

    public void saveDataToDatabase() {
        createFilmJson();
        saveFilmToDatabase();

        createChannelJson();
        saveChannelToDatabase();

        createPackTvJson();
        savePackTvToDatabase();

        createPackInternetJson();
        savePackInternetToDatabase();

        createPackPhoneJson();
        savePackPhoneToDatabase();

        createPackJson();
        savePackToDatabase();

        createUserJson();
        saveUserToDatabase();

//        createBuyFilmJson();
//        saveBuyFilmToDatabase();
    }

    private Long findCategoryIdByName(String name) {
        Category category = categoryRepository.findByName(name).orElse(null);
        if (category != null) {
            return category.getId();
        } else {
            CategoryDto categoryDto = CategoryDto.builder().name(name).build();
            categoryService.addOrUpdateCategory(categoryDto);
            return categoryRepository.findByName(name).get().getId();
        }
    }

    private void saveFilmToDatabase() {
        try (FileReader fileReader = new FileReader(filePath + nameFileFilm)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CreateFilmDto[] films = gson.fromJson(fileReader, CreateFilmDto[].class);

            for (CreateFilmDto q : films) {
                if (filmRepository.findByTitle(q.getTitle()).isEmpty()) {
                    filmService.addOrUpdateFilm(q);
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "GET FILE FILMS JSON");
        }
    }

    private void saveChannelToDatabase() {
        try (FileReader fileReader = new FileReader(filePath + nameFileChannel)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CreateChannelDto[] channels = gson.fromJson(fileReader, CreateChannelDto[].class);

            for (CreateChannelDto q : channels) {
                if (channelRepository.findByName(q.getName()).isEmpty()) {
                    channelService.addOrUpdateChannel(q);
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "GET FILE CHANNELS JSON");
        }
    }

    private void savePackTvToDatabase() {
        try (FileReader fileReader = new FileReader(filePath + nameFileTvPack)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CreatePackTvDto[] tvPacks = gson.fromJson(fileReader, CreatePackTvDto[].class);

            for (CreatePackTvDto q : tvPacks) {
                if (packTvRepository.findByName(q.getName()).isEmpty()) {
                    packTvService.addOrUpdatePackTv(q);
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "GET FILE TV PACKS JSON");
        }
    }

    private void savePackInternetToDatabase() {
        try (FileReader fileReader = new FileReader(filePath + nameFileInternetPack)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CreatePackInternetDto[] internetPacks = gson.fromJson(fileReader, CreatePackInternetDto[].class);

            for (CreatePackInternetDto q : internetPacks) {
                if (packInternetRepository.findByName(q.getName()).isEmpty()) {
                    packInternetService.addOrUpdatePackInternet(q);
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "GET FILE INTERNET PACKS JSON");
        }
    }

    private void savePackPhoneToDatabase() {
        try (FileReader fileReader = new FileReader(filePath + nameFilePhonePack)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CreatePackPhoneDto[] phonePacks = gson.fromJson(fileReader, CreatePackPhoneDto[].class);

            for (CreatePackPhoneDto q : phonePacks) {
                if (packPhoneRepository.findByName(q.getName()).isEmpty()) {
                    packPhoneService.addOrUpdatePackPhone(q);
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "GET FILE PHONE PACKS JSON");
        }
    }

    private void savePackToDatabase() {
        try (FileReader fileReader = new FileReader(filePath + nameFilePack)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CreatePackDto[] packs = gson.fromJson(fileReader, CreatePackDto[].class);

            for (CreatePackDto q : packs) {
                if (packRepository.findByName(q.getName()).isEmpty()) {
                    packService.addOrUpdatePack(q);
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "GET FILE PHONE PACKS JSON");
        }
    }

    private void saveUserToDatabase() {
        try (FileReader fileReader = new FileReader(filePath + nameFileUser)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CreateUserDto[] users = gson.fromJson(fileReader, CreateUserDto[].class);

            for (CreateUserDto q : users) {
                if (userRepository.findByUsername(q.getUsername()).isEmpty()) {
                    userService.registerUser(q, null);
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "GET FILE USERS JSON");
        }
    }

    /*private void saveBuyFilmToDatabase() {
        try (FileReader fileReader = new FileReader(filePath + nameFileBuyFilm)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CreateBuyFilmDto[] buyFilms = gson.fromJson(fileReader, CreateBuyFilmDto[].class);

            for (CreateBuyFilmDto q : buyFilms) {
                //if (buyFilmRepository.findByUserId(q.getUserId()).filter(x-> x.getFilm().getId().equals(q.getFilmId())).isEmpty()) {
                    buyFilmService.addOrUpdateBuyFilm(q);
                //}
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "GET FILE BUY FILM JSON");
        }
    }*/

    private void createFilmJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CreateFilmDto> questions = Arrays.asList(
                    CreateFilmDto.builder()
                            .title("Inwazja: Bitwa o Los Angeles")
                            .yearProduction(2011)
                            .description("Obcy dokonują inwazji na Ziemię. Los Angeles staje się ostatnim bastionem ludzkości.")
                            .price(BigDecimal.valueOf(8.90))
                            .quality(Quality.FULL_HD)
                            .categoryId(findCategoryIdByName("Akcja"))
                            .build(),
                    CreateFilmDto.builder()
                            .title("Karbala")
                            .yearProduction(2015)
                            .description("Rok 2004, siedziba władz lokalnych w irackiej Karbali, City Hall, zostaje zaatakowana przez Al-Kaidę i sadrystów. Grupa polskich oraz bułgarskich żołnierzy odpiera kolejne ataki. ")
                            .price(BigDecimal.valueOf(7.90))
                            .quality(Quality.ULTRA_HD)
                            .categoryId(findCategoryIdByName("Wojenny"))
                            .build(),
                    CreateFilmDto.builder()
                            .title("Life")
                            .yearProduction(2017)
                            .description("Członkowie międzynarodowej wyprawy kosmicznej odkrywają ślady życia na Marsie. Nie wiedzą, że grozi im śmiertelne niebezpieczeństwo. ")
                            .price(BigDecimal.valueOf(9.99))
                            .quality(Quality._4K)
                            .categoryId(findCategoryIdByName("Sci-Fi"))
                            .build(),
                    CreateFilmDto.builder()
                            .title("Helikopter w ogniu")
                            .yearProduction(2001)
                            .description("Oparty na faktach film opowiada o nieudanej akcji amerykańskiego oddziału 120 Delta, który miał za zadanie porwać dwóch poruczników zbuntowanych wojsk Somalii.")
                            .price(BigDecimal.valueOf(7.50))
                            .quality(Quality.FULL_HD)
                            .categoryId(findCategoryIdByName("Wojenny"))
                            .build(),
                    CreateFilmDto.builder()
                            .title("Cisza")
                            .yearProduction(2019)
                            .description("Rodzina pewnej nastolatki poszukuje schronienia na odludziu po tym, jak świat zaatakowały przerażające stworzenia, które polują na ludzi, korzystając ze świetnego słuchu.")
                            .price(BigDecimal.valueOf(11.99))
                            .quality(Quality._4K)
                            .categoryId(findCategoryIdByName("Horror"))
                            .build()
            );

            FileWriter writer = new FileWriter(filePath + nameFileFilm);
            gson.toJson(questions, writer);
            writer.close();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "CREATE FILE FILMS JSON");
        }
    }

    private void createChannelJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CreateChannelDto> channels = Arrays.asList(
                    CreateChannelDto.builder()
                            .name("TVN")
                            .description("Polska stacja prezentująca przede wszystkim programy rozrywkowe i publicystyczne, polskie seriale, filmy fabularne i dokumentalne oraz wydarzenia kulturowe.")
                            .categoryId(findCategoryIdByName("Ogólne"))
                            .build(),
                    CreateChannelDto.builder()
                            .name("Polsat")
                            .description("Najmocniejszymi filarami stacji są: doskonała rozrywka, pasjonujące kino, rzetelna i szybka informacja oraz emocjonujący sport.")
                            .categoryId(findCategoryIdByName("Ogólne"))
                            .build(),
                    CreateChannelDto.builder()
                            .name("Polsat Sport")
                            .description("Całodobowy program sportowy. Transmisje Ligi Mistrzów UEFA i Ligi Europy UEFA.")
                            .categoryId(findCategoryIdByName("Sport"))
                            .build(),
                    CreateChannelDto.builder()
                            .name("Discovery")
                            .description("Programy skupiają się na najbardziej interesujących aspektach nauki i technologii.")
                            .categoryId(findCategoryIdByName("Naukowy"))
                            .build(),
                    CreateChannelDto.builder()
                            .name("4fun TV")
                            .description("Najbardziej muzyczna stacja telewizyjna w Polsce! Kanał skierowany do widzów, których interesują najgorętsze muzyczne trendy i dobra zabawa.")
                            .categoryId(findCategoryIdByName("Rozrywkowy"))
                            .build()
            );
            FileWriter writer = new FileWriter(filePath + nameFileChannel);
            gson.toJson(channels, writer);
            writer.close();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "CREATE FILE CHANNELS JSON");
        }
    }

    private void createPackTvJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CreatePackTvDto> tvPacks = Arrays.asList(
                    CreatePackTvDto.builder()
                            .name("START+")
                            .description("Podstawowa lista kanałów")
                            .price(BigDecimal.valueOf(19.99))
                            .build(),
                    CreatePackTvDto.builder()
                            .name("COMFORT+")
                            .description("Rozszeżona lista kanałów")
                            .price(BigDecimal.valueOf(39.99))
                            .build(),
                    CreatePackTvDto.builder()
                            .name("EXTRA+")
                            .description("Największa lista kanałów")
                            .price(BigDecimal.valueOf(59.99))
                            .build()
            );
            FileWriter writer = new FileWriter(filePath + nameFileTvPack);
            gson.toJson(tvPacks, writer);
            writer.close();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "CREATE FILE TV PACKS JSON");
        }
    }

    private void createPackInternetJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CreatePackInternetDto> internetPacks = Arrays.asList(
                    CreatePackInternetDto.builder()
                            .name("SMALL+")
                            .description("Idealnie rozwiązanie na pozamiejskich terenach")
                            .speedDownload(50)
                            .speedUpload(5)
                            .typeInternet(TypeInternet.linkRadio)
                            .price(BigDecimal.valueOf(19.99))
                            .build(),
                    CreatePackInternetDto.builder()
                            .name("MEDIUM+")
                            .description("Standardowe rozwiązanie, bez limitów")
                            .speedDownload(300)
                            .speedUpload(30)
                            .typeInternet(TypeInternet.cableStandard)
                            .price(BigDecimal.valueOf(39.99))
                            .build(),
                    CreatePackInternetDto.builder()
                            .name("LARGE+")
                            .description("Światłowód, bez limitów")
                            .speedDownload(1000)
                            .speedUpload(100)
                            .typeInternet(TypeInternet.cableFiber)
                            .price(BigDecimal.valueOf(59.99))
                            .build()
            );
            FileWriter writer = new FileWriter(filePath + nameFileInternetPack);
            gson.toJson(internetPacks, writer);
            writer.close();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "CREATE FILE INTERNET PACKS JSON");
        }
    }

    private void createPackPhoneJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CreatePackPhoneDto> phonePacks = Arrays.asList(
                    CreatePackPhoneDto.builder()
                            .name("NAZWA 1+")
                            .description("Podstawowy opis 1")
                            .price(BigDecimal.valueOf(11.11))
                            .limitedConversation("120")
                            .limitedInternet("1")
                            .build(),
                    CreatePackPhoneDto.builder()
                            .name("NAZWA 2+")
                            .description("Podstawowy opis 2")
                            .price(BigDecimal.valueOf(22.22))
                            .limitedConversation("nielimitowane")
                            .limitedInternet("5")
                            .build(),
                    CreatePackPhoneDto.builder()
                            .name("NAZWA 3+")
                            .description("Podstawowy opis 3")
                            .price(BigDecimal.valueOf(33.33))
                            .limitedConversation("nielimitowane")
                            .limitedInternet("nielimitowane")
                            .build()
            );
            FileWriter writer = new FileWriter(filePath + nameFilePhonePack);
            gson.toJson(phonePacks, writer);
            writer.close();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "CREATE FILE PHONE PACKS JSON");
        }
    }

    private void createPackJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CreatePackDto> packs = Arrays.asList(
                    CreatePackDto.builder()
                            .name("START+")
                            .packPhoneId(null)
                            .packInternetId(null)
                            .packTvId(packTvRepository.findByName("START+").orElseThrow(() -> new NullPointerException("not found pack_tv")).getId())
                            .discount(0)
                            .build(),
                    CreatePackDto.builder()
                            .name("COMFORT+")
                            .packPhoneId(null)
                            .packInternetId(null)
                            .packTvId(packTvRepository.findByName("COMFORT+").orElseThrow(() -> new NullPointerException("not found pack_tv")).getId())
                            .discount(0)
                            .build(),
                    CreatePackDto.builder()
                            .name("EXTRA+")
                            .packPhoneId(null)
                            .packInternetId(null)
                            .packTvId(packTvRepository.findByName("EXTRA+").orElseThrow(() -> new NullPointerException("not found pack_tv")).getId())
                            .discount(0)
                            .build(),
                    CreatePackDto.builder()
                            .name("SMALL+")
                            .packPhoneId(null)
                            .packInternetId(packInternetRepository.findByName("SMALL+").orElseThrow(() -> new NullPointerException("not found pack_internet")).getId())
                            .packTvId(null)
                            .discount(0)
                            .build(),
                    CreatePackDto.builder()
                            .name("MEDIUM+")
                            .packPhoneId(null)
                            .packInternetId(packInternetRepository.findByName("MEDIUM+").orElseThrow(() -> new NullPointerException("not found pack_internet")).getId())
                            .packTvId(null)
                            .discount(0)
                            .build(),
                    CreatePackDto.builder()
                            .name("LARGE+")
                            .packPhoneId(null)
                            .packInternetId(packInternetRepository.findByName("LARGE+").orElseThrow(() -> new NullPointerException("not found pack_internet")).getId())
                            .packTvId(null)
                            .discount(0)
                            .build(),
                    CreatePackDto.builder()
                            .name("NAZWA 1+")
                            .packPhoneId(packPhoneRepository.findByName("NAZWA 1+").orElseThrow(() -> new NullPointerException("not found pack_phone")).getId())
                            .packInternetId(null)
                            .packTvId(null)
                            .discount(1)
                            .build(),
                    CreatePackDto.builder()
                            .name("NAZWA 2+")
                            .packPhoneId(packPhoneRepository.findByName("NAZWA 2+").orElseThrow(() -> new NullPointerException("not found pack_phone")).getId())
                            .packInternetId(null)
                            .packTvId(null)
                            .discount(2)
                            .build(),
                    CreatePackDto.builder()
                            .name("NAZWA 3+")
                            .packPhoneId(packPhoneRepository.findByName("NAZWA 3+").orElseThrow(() -> new NullPointerException("not found pack_phone")).getId())
                            .packInternetId(null)
                            .packTvId(null)
                            .discount(3)
                            .build(),
                    CreatePackDto.builder()
                            .name("SUPER PAKIET")
                            .description("Opis super pakietu")
                            .packPhoneId(null)
                            .packInternetId(packInternetRepository.findByName("MEDIUM+").orElseThrow(() -> new NullPointerException("not found pack_internet")).getId())
                            .packTvId(packTvRepository.findByName("COMFORT+").orElseThrow(() -> new NullPointerException("not found pack_tv")).getId())
                            .discount(15)
                            .build(),
                    CreatePackDto.builder()
                            .name("MEGA PAKIET")
                            .description("Opis mega pakietu")
                            .packPhoneId(packPhoneRepository.findByName("NAZWA 3+").orElseThrow(() -> new NullPointerException("not found pack_phone")).getId())
                            .packInternetId(packInternetRepository.findByName("LARGE+").orElseThrow(() -> new NullPointerException("not found pack_internet")).getId())
                            .packTvId(packTvRepository.findByName("EXTRA+").orElseThrow(() -> new NullPointerException("not found pack_tv")).getId())
                            .discount(40)
                            .build()
            );
            FileWriter writer = new FileWriter(filePath + nameFilePack);
            gson.toJson(packs, writer);
            writer.close();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "CREATE FILE PACKS JSON");
        }
    }

    private void createUserJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CreateUserDto> users = Arrays.asList(
                    CreateUserDto.builder()
                            .username("a")
                            .password("123")
                            .passwordConfirmation("123")
                            .email("a@admin.com")
                            .enabled(true)
                            .role(Role.ADMIN)
                            .build(),
                    CreateUserDto.builder()
                            .username("u1")
                            .password("123")
                            .passwordConfirmation("123")
                            .email("u1@user.com")
                            .enabled(true)
                            .role(Role.USER)
                            .build(),
                    CreateUserDto.builder()
                            .username("u2")
                            .password("123")
                            .passwordConfirmation("123")
                            .email("u2@user.com")
                            .enabled(true)
                            .role(Role.USER)
                            .build(),
                    CreateUserDto.builder()
                            .username("u3")
                            .password("123")
                            .passwordConfirmation("123")
                            .email("u3@user.com")
                            .enabled(false)
                            .role(Role.USER)
                            .build()
            );
            FileWriter writer = new FileWriter(filePath + nameFileUser);
            gson.toJson(users, writer);
            writer.close();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "CREATE FILE USER JSON");
        }
    }

    /*private void createBuyFilmJson() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            List<CreateBuyFilmDto> users = Arrays.asList(
                    CreateBuyFilmDto.builder()
                            .dateStart(LocalDate.now())
                            .dateEnd(LocalDate.now().plusDays(7))
                            .filmId(filmRepository.findByTitle("Inwazja: Bitwa o Los Angeles").orElseThrow(() -> new NullPointerException("not found film")).getId())
                            .userId(userRepository.findByUsername("u1").orElseThrow(() -> new NullPointerException("not found user")).getId())
                            .build(),
                    CreateBuyFilmDto.builder()
                            .dateStart(LocalDate.now().minusDays(9))
                            .dateEnd(LocalDate.now().minusDays(2))
                            .filmId(filmRepository.findByTitle("Karbala").orElseThrow(() -> new NullPointerException("not found film")).getId())
                            .userId(userRepository.findByUsername("u1").orElseThrow(() -> new NullPointerException("not found user")).getId())
                            .build()
            );
            FileWriter writer = new FileWriter(filePath + nameFileBuyFilm);
            gson.toJson(users, writer);
            writer.close();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "CREATE FILE BUY FILM JSON");
        }
    }*/
}
