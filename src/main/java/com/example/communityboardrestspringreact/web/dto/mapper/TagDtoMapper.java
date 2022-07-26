package com.example.communityboardrestspringreact.web.dto.mapper;

import com.example.communityboardrestspringreact.domain.Tag;
import com.example.communityboardrestspringreact.web.dto.request.TagRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagDtoMapper {

    TagDtoMapper MAPPER = Mappers.getMapper(TagDtoMapper.class);

    Tag toEntity(TagRequest tagRequest);
}
