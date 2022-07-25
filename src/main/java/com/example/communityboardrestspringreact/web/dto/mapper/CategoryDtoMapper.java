package com.example.communityboardrestspringreact.web.dto.mapper;

import com.example.communityboardrestspringreact.domain.Category;
import com.example.communityboardrestspringreact.web.dto.request.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    CategoryDtoMapper MAPPER = Mappers.getMapper(CategoryDtoMapper.class);


    Category toEntity(CategoryRequest request);
}
