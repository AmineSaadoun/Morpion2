package com.example.morpion2;

public class Config {
	
	public Config(String level, int hiddenLayerSize, int numberOfhiddenLayers, double learningRate) {
		super();
		this.level = level;
		this.hiddenLayerSize = hiddenLayerSize;
		this.numberOfhiddenLayers = numberOfhiddenLayers;
		this.learningRate = learningRate;
	}

    public Config(int l, double lr, int h) {
    }

    @Override
	public String toString() {
		return "Config [level=" + level + ", hiddenLayerSize=" + hiddenLayerSize + ", numberOfhiddenLayers="
				+ numberOfhiddenLayers + ", learningRate=" + learningRate + "]";
	}

	//FIELDS ...
	public String level ;
	public int hiddenLayerSize;
	public int numberOfhiddenLayers;
	public double learningRate;
}
