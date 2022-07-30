package com.example.communityboardrestspringreact.web.dto.mapper;

import com.example.communityboardrestspringreact.domain.Account;
import com.example.communityboardrestspringreact.web.dto.request.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {

    AccountDtoMapper MAPPER = Mappers.getMapper(AccountDtoMapper.class);


    @Mapping(target = "password", ignore = true)
    Account toEntity(SignUpRequest request);
}
