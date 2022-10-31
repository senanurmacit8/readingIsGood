package com.book.reading.mapper;

import com.book.reading.dto.user.UserInfo;
import com.book.reading.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy= NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserInfo mapToUserDTO(User user);

    User mapToUser(UserInfo userInfo);

}
