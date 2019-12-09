package com.hongbao.bloons.factories;

import com.hongbao.bloons.entities.Bloon;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BloonFactory {

	public static Bloon createRedBloon() {
		return new Bloon(Bloon.Color.RED, 1, 5, false, false);
	}

	public static Bloon createRedCamoBloon() {
		return new Bloon(Bloon.Color.RED, 1, 5, true, false);
	}

	public static Bloon createRedRegenBloon() {
		return new Bloon(Bloon.Color.RED, 1, 5, false, true);
	}

	public static Bloon createRedCamoRegenBloon() {
		return new Bloon(Bloon.Color.RED, 1, 5, true, true);
	}

	public static Bloon createBlueBloon() {
		return new Bloon(Bloon.Color.BLUE, 2, 6, false, false);
	}

	public static Bloon createBlueCamoBloon() {
		return new Bloon(Bloon.Color.BLUE, 2, 6, true, false);
	}

	public static Bloon createBlueRegenBloon() {
		return new Bloon(Bloon.Color.BLUE, 2, 6, false, true);
	}

	public static Bloon createBlueCamoRegenBloon() {
		return new Bloon(Bloon.Color.BLUE, 2, 6, true, true);
	}

	public static Bloon createGreenBloon() {
		return new Bloon(Bloon.Color.GREEN, 3, 7, false, false);
	}

	public static Bloon createGreenCamoBloon() {
		return new Bloon(Bloon.Color.GREEN, 3, 7, true, false);
	}

	public static Bloon createGreenRegenBloon() {
		return new Bloon(Bloon.Color.GREEN, 3, 7, false, true);
	}

	public static Bloon createGreenCamoRegenBloon() {
		return new Bloon(Bloon.Color.GREEN, 3, 7, true, true);
	}

	public static Bloon createYellowBloon() {
		return new Bloon(Bloon.Color.YELLOW, 4, 8, false, false);
	}

	public static Bloon createYellowCamoBloon() {
		return new Bloon(Bloon.Color.YELLOW, 4, 8, true, false);
	}

	public static Bloon createYellowRegenBloon() {
		return new Bloon(Bloon.Color.YELLOW, 4, 8, false, true);
	}

	public static Bloon createYellowCamoRegenBloon() {
		return new Bloon(Bloon.Color.YELLOW, 4, 8, true, true);
	}

	public static Bloon createPinkBloon() {
		return new Bloon(Bloon.Color.PINK, 5, 9, false, false);
	}

	public static Bloon createPinkCamoBloon() {
		return new Bloon(Bloon.Color.PINK, 5, 9, true, false);
	}

	public static Bloon createPinkRegenBloon() {
		return new Bloon(Bloon.Color.PINK, 5, 9, false, true);
	}

	public static Bloon createPinkCamoRegenBloon() {
		return new Bloon(Bloon.Color.PINK, 5, 9, true, true);
	}

	public static Bloon createRandomBloon() {
		Random r = new Random();
		List<Bloon.Color> colors = Arrays.asList(Bloon.Color.RED, Bloon.Color.BLUE, Bloon.Color.GREEN, Bloon.Color.YELLOW, Bloon.Color.PINK);

		int index = r.nextInt(colors.size());
		return new Bloon(colors.get(index), index + 1, index + 5, r.nextBoolean(), r.nextBoolean());
	}

}
