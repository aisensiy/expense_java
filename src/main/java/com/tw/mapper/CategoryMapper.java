package com.tw.mapper;

import com.tw.domain.Category;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    int createCategory(@Param("userId") int userId, @Param("category") Category category);
}
