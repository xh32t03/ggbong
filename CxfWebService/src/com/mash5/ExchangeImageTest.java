package com.mash5;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ExchangeImageTest {

	public static void saveImageAsMini(String fromFileStr, String saveToFileStr, int width, int hight) throws Exception {
		BufferedImage srcImage;
		String imgType = "JPEG";
		if (fromFileStr.toLowerCase().endsWith(".png")) {
			imgType = "PNG";
		}else if(fromFileStr.toLowerCase().endsWith(".gif")) {
			imgType = "GIF";
		}
		// System.out.println(ex);
		File saveFile = new File(saveToFileStr);
		File fromFile = new File(fromFileStr);
		srcImage = ImageIO.read(fromFile);
		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight);
//			srcImage = exchangeAvatar(srcImage, width, hight);
		}
		
		ImageIO.write(srcImage, imgType, saveFile);
	}

//	//测试前背景透明设置
//	public static BufferedImage exchangeAvatar(BufferedImage source, int targetW, int targetH){
//		// 创建BufferedImage对象
//		BufferedImage bufferedImage = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_RGB);
//		Image image = source.getScaledInstance(targetW, targetH, Image.SCALE_DEFAULT);
//		// 获取Graphics2D
//		Graphics2D g2d = bufferedImage.createGraphics();
//		
//		// ---------- 增加下面的代码使得背景透明 -----------------
//		bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(targetW, targetH, Transparency.BITMASK);
//		g2d.dispose();
//		g2d = bufferedImage.createGraphics();
//		// ---------- 背景透明代码结束 -----------------
//		
//		// 前景设置透明度变化：AlphaComposite.getInstance的第二个参数为透明度 ，值从0-1.0，依次变得不透明
//		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.3f));
//		
//		// 画图
//		g2d.setColor(new Color(255,0,0));
//		g2d.setStroke(new BasicStroke(0));
//		g2d.drawImage(image, 0, 0, null);
//		//透明度设置 结束
//		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
//		//释放对象
//		g2d.dispose();
//		
//		return bufferedImage;
//	}
	
	/**
	 * 把头像转换为60*60的缩略图
	 * 
	 * @param source 大图文件
	 * @param targetW
	 * @param targetH
	 * @return BufferedImage
	 */
	public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // 未识别出图像类型，自定义图像类型
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		}else{
			target = new BufferedImage(targetW, targetH, type);
		}
		Graphics2D g2d = target.createGraphics();
		// smoother than exlax:
		
		//设置圆角
		GeneralPath path = new GeneralPath();
		RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, targetW, targetH, 200, 200);
		path.append(rect, false);
//		Color color = new Color(0,0,0,0);
//		g2d.setBackground(color);
//		g2d.setColor(color);
		g2d.setBackground(Color.WHITE);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, targetW, targetH);
		//setComposite()方法指定新的像素如何在呈现过程中与图形设备上的现有像素组合
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2d.setClip(path);
//		g2d.clip(path);
		
//		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);//选择更高的质量的绘图算法
//		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//消除形状锯齿状特征
		
//		g2d.draw(path);
//		g2d.drawImage(source, 0, 0, null);
		g2d.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g2d.dispose();
		return target;
		
		
		
//		int iw = target.getWidth();//图片宽
//		int ih = target.getHeight();//图片高
//		int[] pixels = new int[iw*ih];//存放像素的数组
//		ColorModel cm = ColorModel.getRGBdefault();
//		Color color = new Color(0.0f, 0.0f, 0.0f);//要透明的颜色16进制的颜色值
//		for(int i=0; i <iw*ih; i++){
//			int red,green,blue;
//			red = cm.getRed(pixels[i]);
//			green = cm.getGreen(pixels[i]);
//			blue = cm.getBlue(pixels[i]);
//			int rgb = cm.getRGB(pixels[i]);
//			//比较当前的像素的rgb值是否与给定颜色的rgb值相等，假如相等，则把这个颜色变透明
//			if(color.getRGB() == rgb){
//				pixels[i]=red <<16 | green <<8 |blue;
//			}
//		}
//		//将数组中的象素产生一个图像
//		ImageProducer ip = new MemoryImageSource(iw,ih,pixels,0,iw);
//		Image image2 = Image.createImage(ip);//根据处理完的像素创建图像。
//		repaint(); //重绘图像，或者重写图像到另外一个位置 
		
		
//		ImageIcon imageIcon = new ImageIcon(target);
//		BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),BufferedImage.TYPE_4BYTE_ABGR);
//		Graphics2D g2d2 = (Graphics2D) bufferedImage.getGraphics();
//		int alpha = 0;
//		for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
//			for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
//				int rgb = bufferedImage.getRGB(j2, j1);
//				int R = (rgb & 0xff0000) << 16;
//				int G = (rgb & 0xff00) << 8;
//				int B = (rgb & 0xff);
//				if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
//					rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
//				}
//			}
//		}
//		g2d2.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
//		return bufferedImage;
	}
	
	//test
	public static void main(String argv[]) {
		try {
			// 参数1(from),参数2(to),参数3(宽),参数4(高)
			ExchangeImageTest.saveImageAsMini("D:/temp/collection.jpg", "D:/temp/2.jpg",360, 360);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
