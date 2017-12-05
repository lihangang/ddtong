package com.ddtong.ddtfw.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

@Controller
@RequestMapping(value = "/{zdpath}/ddtfw/barcode")
public class BarcodeController {

	private static final Logger logger = LoggerFactory.getLogger(BarcodeController.class);
	
	/**
	 * 生成条形码/二维码
	 * @param uid
	 * @param ticket
	 * @param barcode
	 * @param type	0：一维码 1：二维码
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/biuldbarcodeimage", produces="image/jpeg")
	public void getBarcodeImage (
			@PathVariable("zdpath") String zdpath,
			@RequestParam(value = "barcode", required = true) String barcode,
			@RequestParam(value = "type", required = true, defaultValue="0") int type,
			@RequestParam(value = "width", required = false, defaultValue="425") int width,
			@RequestParam(value = "height", required = false, defaultValue="125") int height,
			HttpServletResponse response) {
		
		logger.info(zdpath + "进入生成条形码/二维码");
		try {
			if(StringUtils.isEmpty(barcode)) {
				return;
			}
			BufferedImage buImage  = null;
			if(type == 0){		
				//条码号为空或条码号非字母或数字
				if(StringUtils.isEmpty(barcode)) {
					//条码号不存在
					return;
				} 
				//生成条形码  
	            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
	            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
//	            int width = 425, height = 125;
	            BitMatrix bitMatrix = new MultiFormatWriter().encode(barcode, BarcodeFormat.CODE_128, width, height, hints);  
	            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						image.setRGB(x, y, bitMatrix.get(x, y) ? 0xff000000 : 0xFFFFFFFF);
					}
				}
	            BufferedImage newimage = new BufferedImage(width + 10, height + 45, BufferedImage.TYPE_INT_RGB);
	            Graphics2D g = (Graphics2D) newimage.getGraphics();
	            g.setColor(Color.WHITE);
	            g.fillRect(0, 0, width + 10, height + 45);
	            g.drawImage(image, 5, 5, width, height, null);
	            Font font = new Font("宋体", Font.PLAIN, 30);
	            g.setFont(font);
	            g.setColor(Color.BLACK);
	            g.drawString(barcode, (width - g.getFontMetrics(font).stringWidth(barcode)) / 2, height + 35);
	            buImage = newimage;
				
			}else if (type == 1){
				//生成二维码
				MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

				Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
				hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
				BitMatrix bitMatrix = multiFormatWriter.encode(barcode, BarcodeFormat.QR_CODE, width, height, hints);
				int w = bitMatrix.getWidth();
				int h = bitMatrix.getHeight();
				BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				for (int x = 0; x < w; x++) {
					for (int y = 0; y < h; y++) {
						image.setRGB(x, y, bitMatrix.get(x, y) ? 0xff000000 : 0xFFFFFFFF);
					}
				}
				buImage = image;
			}else {
				return;
			}
			ImageIO.write(buImage, "jpg", response.getOutputStream());
		} catch (Exception e) {
			logger.error("biuldbarcodeimage", e);
		}	
	}
}
