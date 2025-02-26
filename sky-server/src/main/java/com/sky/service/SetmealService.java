package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;

public interface SetmealService {


    void saveWithDish(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void deleteBatch(Long[] ids);

    void startOrStop(Integer status, Long id);

    void update(SetmealDTO setmealDTO);

    SetmealVO getByIdWithDish(Long id);
}
