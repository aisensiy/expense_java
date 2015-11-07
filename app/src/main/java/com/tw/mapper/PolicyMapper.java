package com.tw.mapper;

import com.tw.domain.Policy;
import org.apache.ibatis.annotations.Param;

public interface PolicyMapper {
    int createPolicy(@Param("categoryId") int categoryId, @Param("policy") Policy policy);
}
