package com.li.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.empty.Stock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LiXL
 * @date 2024/3/16
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {

    Stock selectForUpdate(String productCode);

    int updateStock(@Param("productCode") String productCode, @Param("count") int count);
}
