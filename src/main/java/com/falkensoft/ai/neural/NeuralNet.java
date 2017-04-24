package com.falkensoft.ai.neural;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Falken224 on 4/22/2017.
 */
public class NeuralNet {

    private Map<Integer,Double> valCache = new HashMap<>();

    Function<Double,Double> activationFunction;
    int inputCount;
    int outputNodeIndex;
    int totalNodes;
    List<Connection> connectionList;

    NeuralNet(){}

    public static class Connection
    {
        public int source;
        public int destination;
        public double weight;

        Connection(int src, int dest, double wgt)
        {
            this.source = src;
            this.destination = dest;
            this.weight = wgt;
        }
    }

    public static NeuralNetBuilder define()
    {
        return new NeuralNetBuilder();
    }

    public NeuralNet setInputs(double... inputs)
    {
        if(inputs==null || inputs.length!=inputCount)
            throw new IllegalArgumentException("New input values MUST be the same quantity as the defined inputs to the network.");
        for(int loop=0; loop<inputs.length; loop++)
        {
            final int index = loop;
            Connection conn = connectionList.stream()
                              .filter(item -> (item.source==0) && (item.destination==index+1))
                              .findFirst()
                              .get();
            conn.weight=inputs[loop];
        }
        valCache.clear();
        return this;
    }

    public static double getNodeValue(List<Connection> connectionList, int nodeId, Function <Double, Double> activation, Map<Integer,Double> valueCache)
    {
        if(!valueCache.containsKey(nodeId)) {
            double value = connectionList.stream()
                    .filter(item -> item.destination == nodeId)
                    .mapToDouble(item -> item.source == 0 ?
                            item.weight :
                            item.weight * getNodeValue(connectionList, item.source, activation, valueCache))
                    .sum();
            if(connectionList.stream().filter(item -> item.destination==nodeId && item.source!=0).count()==0)
                valueCache.put(nodeId,value); //This is an input node.  We don't do activation.
            else
                valueCache.put(nodeId,activation.apply(value));
        }
        return valueCache.get(nodeId);
    }

    public double getOutput(int output)
    {
        return getNodeValue(connectionList,output + outputNodeIndex + 1,activationFunction,valCache);
    }
}
