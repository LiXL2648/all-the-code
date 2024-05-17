package com.li.empty;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author LiXL
 * @date 2024/5/17
 */
@Data
@Accessors(chain = true)
@TableName("db_lock")
public class Lock {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String lockName;
}
