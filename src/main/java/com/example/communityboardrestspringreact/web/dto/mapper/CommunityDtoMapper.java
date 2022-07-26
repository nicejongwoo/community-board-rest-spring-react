package com.example.communityboardrestspringreact.web.dto.mapper;

import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.web.dto.request.CommunityRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommunityDtoMapper {

    CommunityDtoMapper MAPPER = Mappers.getMapper(CommunityDtoMapper.class);

    @Mapping(target = "tags", ignore = true)
    Community toEntity(CommunityRequest request);

}
