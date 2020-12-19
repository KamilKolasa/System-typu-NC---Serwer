package com.app.service.model;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.Category;
import com.app.model.Channel;
import com.app.model.dto.create.CreateChannelDto;
import com.app.model.dto.get.GetChannelDto;
import com.app.repository.CategoryRepository;
import com.app.repository.ChannelRepository;
import com.app.service.converter.MapperModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final CategoryRepository categoryRepository;

    public CreateChannelDto addOrUpdateChannel(CreateChannelDto createChannelDto) {
        if (createChannelDto == null) {
            throw new MyException(ExceptionCode.SERVICE, "ADD or UPDATE CHANNEL - CHANNEL IS NULL");
        }
        Channel channel = MapperModel.fromCreateChannelDtoToChannel(createChannelDto);

        Category category = categoryRepository.findById(createChannelDto.getCategoryId()).orElseThrow(() -> new NullPointerException("NOT FOUND CATEGORY"));
        channel.setCategory(category);

        return MapperModel.fromChannelToCreateChannelDto(channelRepository.save(channel));
    }

    public GetChannelDto deleteChannel(Long id) {
        if (id == null) {
            throw new MyException(ExceptionCode.SERVICE, "DELETE CHANNEL - ID IS NULL");
        }
        Channel channel = channelRepository.getOne(id);
        channelRepository.delete(channel);
        return MapperModel.fromChannelToGetChannelDto(channel);
    }

    public List<GetChannelDto> getAllChannel() {
        return channelRepository
                .findAll()
                .stream()
                .map(MapperModel::fromChannelToGetChannelDto)
                .collect(Collectors.toList());
    }
}
