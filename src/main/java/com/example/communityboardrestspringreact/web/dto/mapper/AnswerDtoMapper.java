package com.example.communityboardrestspringreact.web.dto.mapper;

import com.example.communityboardrestspringreact.domain.Answer;
import com.example.communityboardrestspringreact.web.dto.response.AnswerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerDtoMapper {

    AnswerDtoMapper MAPPER = Mappers.getMapper(AnswerDtoMapper.class);

    List<AnswerResponse> toDtoList(List<Answer> answers);

}
