package com.tw.mapper;

import com.tw.domain.Approvement;

public interface ApprovementMapper {
    int createApprovement(int requestId, Approvement approvement);
}
