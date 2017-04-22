package com.falkensoft.ai.neural;

/**
 * Created by Falken224 on 4/21/2017.
 */
public class ActivationFunctions {
    public static Double sigmoid(Double input)
    {
        return 1.0d / ( 1.0d + Math.pow(Math.E,-1.0d * input));
    }

    public static Double binary(Double input)
    {
        if(input>0.0d)
            return 1.0d;
        return 0.0d;
    }
}
