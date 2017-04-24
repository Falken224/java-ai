package com.falkensoft.ai.neural;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Falken224 on 4/22/2017.
 */
public class NeuralNetBuilder {

    private Function<Double,Double> activationFunction=ActivationFunctions::sigmoid;
    private Integer inputCount=1;
    private Integer outputCount=1;
    private double[] weightMatrix;

    NeuralNetBuilder(){}

    public NeuralNet build()
    {
        List<NeuralNet.Connection> connections = new ArrayList<>();
        NeuralNet ret = new NeuralNet();
        ret.inputCount = inputCount;
        ret.activationFunction = activationFunction;
        int weightIndex=0;
        int nodeId=1;
        for(int input=0; input<inputCount; input++)
        {
            connections.add(new NeuralNet.Connection(0,nodeId++,0));
        }
        for(int output=0; output<outputCount; output++)
        {
            for(int input=1; input<=inputCount; input++)
            {
                connections.add(new NeuralNet.Connection(input,nodeId,weightMatrix[weightIndex++]));
            }
            connections.add(new NeuralNet.Connection(0,nodeId,-weightMatrix[weightIndex++]));
        }
        ret.connectionList = connections;
        ret.totalNodes = nodeId;
        ret.outputNodeIndex = ret.totalNodes-outputCount;
        return ret;
    }

    public NeuralNetBuilder inputs(int inputCount)
    {
        this.inputCount = inputCount;
        return this;
    }

    public NeuralNetBuilder outputs(int outputCount)
    {
        this.outputCount = outputCount;
        return this;
    }

    public NeuralNetBuilder activation(Function<Double,Double> func)
    {
        this.activationFunction = func;
        return this;
    }

    public NeuralNetBuilder weightMatrix(double... weightMatrix)
    {
        this.weightMatrix = weightMatrix;
        return this;
    }
}
