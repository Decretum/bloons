package com.hongbao.bloons.factories;

import com.hongbao.bloons.entities.Bloon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BloonFactory {

	public static Bloon createRedBloon() {
		return new Bloon(Bloon.Color.RED, 1, false, false);
	}

	public static Bloon createRedCamoBloon() {
		return new Bloon(Bloon.Color.RED, 1, true, false);
	}

	public static Bloon createRedRegenBloon() {
		return new Bloon(Bloon.Color.RED, 1, false, true);
	}

	public static Bloon createRedCamoRegenBloon() {
		return new Bloon(Bloon.Color.RED, 1, true, true);
	}

	public static Bloon createBlueBloon() {
		return new Bloon(Bloon.Color.BLUE, 2, false, false);
	}

	public static Bloon createBlueCamoBloon() {
		return new Bloon(Bloon.Color.BLUE, 2, true, false);
	}

	public static Bloon createBlueRegenBloon() {
		return new Bloon(Bloon.Color.BLUE, 2, false, true);
	}

	public static Bloon createBlueCamoRegenBloon() {
		return new Bloon(Bloon.Color.BLUE, 2, true, true);
	}

	public static Bloon createGreenBloon() {
		return new Bloon(Bloon.Color.GREEN, 3, false, false);
	}

	public static Bloon createGreenCamoBloon() {
		return new Bloon(Bloon.Color.GREEN, 3, true, false);
	}

	public static Bloon createGreenRegenBloon() {
		return new Bloon(Bloon.Color.GREEN, 3, false, true);
	}

	public static Bloon createGreenCamoRegenBloon() {
		return new Bloon(Bloon.Color.GREEN, 3, true, true);
	}

	public static Bloon createYellowBloon() {
		return new Bloon(Bloon.Color.YELLOW, 4, false, false);
	}

	public static Bloon createYellowCamoBloon() {
		return new Bloon(Bloon.Color.YELLOW, 4, true, false);
	}

	public static Bloon createYellowRegenBloon() {
		return new Bloon(Bloon.Color.YELLOW, 4, false, true);
	}

	public static Bloon createYellowCamoRegenBloon() {
		return new Bloon(Bloon.Color.YELLOW, 4, true, true);
	}

	public static Bloon createPinkBloon() {
		return new Bloon(Bloon.Color.PINK, 5, false, false);
	}

	public static Bloon createPinkCamoBloon() {
		return new Bloon(Bloon.Color.PINK, 5, true, false);
	}

	public static Bloon createPinkRegenBloon() {
		return new Bloon(Bloon.Color.PINK, 5, false, true);
	}

	public static Bloon createPinkCamoRegenBloon() {
		return new Bloon(Bloon.Color.PINK, 5, true, true);
	}

	public static Bloon createRandomBloon() {
		Random r = new Random();
		List<Bloon.Color> colors = Arrays.asList(Bloon.Color.RED, Bloon.Color.BLUE, Bloon.Color.GREEN, Bloon.Color.YELLOW, Bloon.Color.PINK);

		int index = r.nextInt(colors.size());
		return new Bloon(colors.get(index), index + 1, r.nextBoolean(), r.nextBoolean());
	}

}
