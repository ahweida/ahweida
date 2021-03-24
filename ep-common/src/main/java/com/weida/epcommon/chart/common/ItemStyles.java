package com.weida.epcommon.chart.common;

import lombok.Data;

/**
 * @Auther: zhaof
 * @Date: 2021/1/26 9:05
 * @Desc: TODO
 */
@Data
public class ItemStyles {
    public static ItemStyle newItemStyle(){
       return new ItemStyle();
    }


    public static ItemStyle getDefaultItemStyle(){
        ItemStyle itemStyle = newItemStyle();
        TextStyle textStyle = new TextStyle();
        Label label = new Label();
        label.setTextStyle(textStyle);
        Normal normal = new Normal();
        normal.setLabel(label);
        itemStyle.setNormal(normal);
        return  itemStyle;
    }

}
