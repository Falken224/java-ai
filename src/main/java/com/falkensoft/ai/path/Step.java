package com.falkensoft.ai.path;

import java.util.function.ToDoubleFunction;

/**
 * Created by Falken224 on 5/2/2017.
 */
public class Step<T> {
    private Step<T> previous;
    private Double currentScore=0d;
    private Double totalScore=0d;
    private T loc;

    public Step(T location, Step<T> previous, Double stepCost)
    {
        this.loc = location;
        this.previous = previous;
        if(previous!=null) currentScore=previous.currentScore;
        currentScore+=stepCost;
    }

    public Step<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Step<T> previous) {
        this.previous = previous;
    }

    public Double getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Double currentScore) {
        this.currentScore = currentScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public T getLoc() {
        return loc;
    }

    public void setLoc(T loc) {
        this.loc = loc;
    }

    public Step<T> calcScore(ToDoubleFunction<T> remainingScoreFunction)
    {
        totalScore = currentScore + remainingScoreFunction.applyAsDouble(loc);
        return this;
    }
}
