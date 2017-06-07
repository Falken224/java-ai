package com.falkensoft.ai.neural.training;

import com.falkensoft.ai.util.NVector;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Falken224 on 5/3/2017.
 */
public class TrainingData {
    public List<Pair<NVector,NVector>> trainingSet = new ArrayList<>();

    public void addTrainingSet(NVector in, NVector out)
    {
        trainingSet.add(new ImmutablePair<>(in,out));
    }
}
