package com.example.communityboardrestspringreact.web.dto.mapper;

import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import com.example.communityboardrestspringreact.web.dto.response.TestResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TestDtoMapper {

    TestDtoMapper MAPPER = Mappers.getMapper(TestDtoMapper.class);

    Test toEntity(TestRequest request);

    TestResponse toDto(Test test);
}
