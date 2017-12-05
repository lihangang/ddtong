package com.ddtong.ddtfw.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.GlyphsPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.GlyphsVisitors;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.OverlapGlyphsUsingShapeVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.TranslateAllToRandomPointVisitor;
import com.octo.captcha.component.image.textpaster.glyphsvisitor.TranslateGlyphsVerticalRandomVisitor;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class CaptchaEngineEx extends ListImageCaptchaEngine {

	protected void buildInitialFactories() {
		int minWordLength = 4;
		int maxWordLength = 4;
		int fontSize = 35;
		int imageWidth = 105;
		int imageHeight = 35;

		// 随机 文字
		WordGenerator wordGenerator = new RandomWordGenerator("123456789abcdefghijklmnpqrstuvwxyz");

		// 文字
		TextPaster randomPaster = new GlyphsPaster(minWordLength, maxWordLength,
				new RandomListColorGenerator(new Color[] { new Color(23, 170, 27), new Color(220, 34, 11), new Color(23, 67, 172) }), new GlyphsVisitors[] {
						new TranslateGlyphsVerticalRandomVisitor(1), new OverlapGlyphsUsingShapeVisitor(3), new TranslateAllToRandomPointVisitor() });

		// 背景
		BackgroundGenerator background = new UniColorBackgroundGenerator(imageWidth, imageHeight, Color.white);

		// 字体
		FontGenerator font = new RandomFontGenerator(fontSize, fontSize, new Font[] { new Font("nyala", Font.BOLD, fontSize),
				new Font("Bell MT", Font.PLAIN, fontSize), new Font("Credit valley", Font.BOLD, fontSize) });

		ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[] {});
		ImageDeformation backDef = new ImageDeformationByFilters(new ImageFilter[] {});
		ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[] {});

		WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef, postDef);

		// false 不区分大小写,即忽略大小写
		addFactory(new GimpyFactory(wordGenerator, word2image, false));

	}
}
