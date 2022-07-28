package com.example.communityboardrestspringreact.web.dto.mapper;

import com.example.communityboardrestspringreact.domain.Comment;
import com.example.communityboardrestspringreact.web.dto.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentDtoMapper {

    CommentDtoMapper MAPPER = Mappers.getMapper(CommentDtoMapper.class);

    List<CommentResponse> toDtoList(List<Comment> comments);

}
