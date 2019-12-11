package com.hongbao.bloons;

import com.hongbao.bloons.entities.Bloon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BloonQueue {
	
	// Defines a sequence of bloons appearing on the map as well as when they should appear
	
	private List<Bloon> bloons;
	private List<Long> intervals;
	private int currentIndex;
	private int clock;
	
	public BloonQueue(List<Bloon> bloons, List<Long> intervals) {
		this.bloons = bloons;
		this.intervals = intervals;
		currentIndex = 0;
		clock = 0;
	}
	
	public Set<Bloon> getBloons() {
		HashSet<Bloon> generatedBloons = new HashSet<>();
		while (currentIndex < bloons.size()) {
			if (intervals.get(currentIndex) == clock) {
				generatedBloons.add(bloons.get(currentIndex));
				currentIndex++;
			} else {
				break;
			}
		}
		clock++;
		return generatedBloons;
	}
	
}
