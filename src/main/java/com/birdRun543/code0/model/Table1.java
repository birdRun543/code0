package com.birdRun543.code0.model;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 规格类型
 * </p>
 *
 * @author hanbing
 * @since 2021-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Table1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer productId;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    /**
     * 套餐类型（字典）
     */
    private Integer packageId;

    /**
     * 服务周期（天）
     */
    private Integer serviceType;

    /**
     * 商品现价
     */
    private BigDecimal priceFinal;

    /**
     * 套餐原价
     */
    private BigDecimal priceOrigin;

}
