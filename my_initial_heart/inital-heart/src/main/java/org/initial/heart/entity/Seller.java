package org.initial.heart.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_seller")
public class Seller {
    @TableId("seller_id")
    private String sellerId;
    @TableField("name")
    private String name;
    @TableField("nick_name")
    private String nickName;
    @TableField("password")
    private String password;
    @TableField("status")
    private String status;
    @TableField("address")
    private String address;
    @TableField("create_time")
    private Date createTime;

}
