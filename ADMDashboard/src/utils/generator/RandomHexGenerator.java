package utils.generator;

import java.awt.Color;
import java.util.Random;

import utils.converter.DatatypeConverter;

public class RandomHexGenerator {
	
	private static long seed = System.nanoTime();
	private static Random rand = new Random(seed);
	final static float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
	final static float luminance = 1.0f; //1.0 for brighter, 0.0 for black
	
	public static String newHex(){
		rand.setSeed(++seed);
		int R = rand.nextInt(256);
		int G = rand.nextInt(256);
		int B = rand.nextInt(256);
		float hue = rand.nextFloat();
		Color color = new Color(R, G, B); //random color, but can be bright or dull

		//to get rainbow, pastel colors
		color = Color.getHSBColor(hue, saturation, luminance);
		
		return DatatypeConverter.toHex(color);
		
	}
}
