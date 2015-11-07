package com.tw.mapper;

import com.tw.domain.Approvement;
import org.apache.ibatis.annotations.Param;

public interface ApprovementMapper {
    int createApprovement(@Param("requestId") int requestId, @Param("approvement") Approvement approvement);
}
