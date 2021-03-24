package com.weida.epmain.dto.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CenterDataVO {

    private Integer x;

    private Integer y;

    private Double value;
}
