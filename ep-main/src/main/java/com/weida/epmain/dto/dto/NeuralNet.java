package com.weida.epmain.dto.dto;

import lombok.Data;

/**
 * @description:
 * @author: liaoze
 * @date: 2021/1/25 下午1:18
 * @version:
 */
@Data
public class NeuralNet {

    public  double[][] inputWeight;
    public  double[] outputWeight;
    public  double[] bOneArray;
    public  double b2Value;
    public  double[] xMinArray;
    public  double[] xMaxArray;
    public  double xMinValue;
    public  double xMaxValue;

    public  String version;
    public  String name;
    public  boolean isUpdate;

    public  double predictTime = 300;


}
