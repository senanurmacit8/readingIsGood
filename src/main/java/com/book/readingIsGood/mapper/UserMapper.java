package com.book.readingIsGood.mapper;

import com.book.readingIsGood.dto.user.UserDTO;
import com.book.readingIsGood.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy= NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO mapToUserDTO(User user);

}
