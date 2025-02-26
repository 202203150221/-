package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
@Api(tags = "店铺是否营业相关接口")
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置店铺营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public Result setStatus(@PathVariable Integer status){
        if (status == 0){
            redisTemplate.opsForValue().set("SHOP_STATUS",0);
        }else {
            redisTemplate.opsForValue().set("SHOP_STATUS",1);
        }
        log.info("设置店铺状态为{}",status == 0 ? "打烊" : "营业");
        return Result.success();
    }

    /**
     * 获取店铺营业状态
     * @return
     */

    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result getStatus(){
        Integer status = (Integer)redisTemplate.opsForValue().get("SHOP_STATUS");
        if (status == null){
            redisTemplate.opsForValue().set("SHOP_STATUS",1);
            log.info("店铺状态为营业");
        }else {
            log.info("店铺状态为{}",status == 0 ? "打烊" : "营业");
        }

        return Result.success(status);
    }
}
