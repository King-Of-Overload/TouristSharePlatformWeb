package alan.share.utils;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 * 图片裁剪处理
 * @author Alan
 *
 */
public class ImageCliper {
	
	 /* 
     * 根据尺寸图片居中裁剪 
     */  
     public static InputStream cutCenterImage(File src,int w,int h){
    	 InputStream resultStream=null;
    	   try{
         Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");   
         ImageReader reader = (ImageReader)iterator.next();   
         InputStream in=new FileInputStream(src);//原始图片输入流
         ImageInputStream iis = ImageIO.createImageInputStream(in);   
         reader.setInput(iis, true);   
         ImageReadParam param = reader.getDefaultReadParam();   
         int imageIndex = 0;   
         Rectangle rect = new Rectangle((reader.getWidth(imageIndex)-w)/2, (reader.getHeight(imageIndex)-h)/2, w, h);    
         param.setSourceRegion(rect);   
         BufferedImage bi = reader.read(0,param);            
         ByteArrayOutputStream bos=new ByteArrayOutputStream();
         ImageOutputStream imageOut=null;
         imageOut=ImageIO.createImageOutputStream(bos);
         ImageIO.write(bi, "jpg",imageOut);
         resultStream=new ByteArrayInputStream(bos.toByteArray());
         }catch(Exception e){
        	 e.printStackTrace();
         }
    	   return resultStream;
     }
     
     
   /**
    * 图片裁剪通用接口
    * @param src 接收到的文件对象
    * @param x
    * @param y
    * @param w
    * @param h
    */
     public static InputStream cutImage(File src,int x,int y,int w,int h){ 
    	 InputStream resultStream=null;
    	 try{
            Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");   
            ImageReader reader = (ImageReader)iterator.next();   
            InputStream in=new FileInputStream(src);  
            ImageInputStream iis = ImageIO.createImageInputStream(in);   
            reader.setInput(iis, true);   
            ImageReadParam param = reader.getDefaultReadParam();   
            Rectangle rect = new Rectangle(x, y, w,h); 
            param.setSourceRegion(rect);   
            BufferedImage bi = reader.read(0,param);                
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ImageOutputStream imageOut=null;
            imageOut=ImageIO.createImageOutputStream(bos);
            ImageIO.write(bi, "jpg",imageOut);
            resultStream=new ByteArrayInputStream(bos.toByteArray());
    	 }catch(Exception e){
    		 throw new RuntimeException(e);
    	 }
    	 return resultStream;
     }   
     
    /**
     * 图片缩放
     * @param src 源文件
     * @param w 宽
     * @param h 高
     * @throws Exception
     */
     public static void zoomImage(String src,String dest,int w,int h){  
         double wr=0,hr=0;  
         try {  
         File srcFile = new File(src);  
         File destFile = new File(dest);  
         BufferedImage bufImg = ImageIO.read(srcFile);  
         Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);  
         wr=w*1.0/bufImg.getWidth();  
         hr=h*1.0 / bufImg.getHeight();  
         AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);  
         Itemp = ato.filter(bufImg, null);  
             ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);  
         } catch (Exception ex) {  
             ex.printStackTrace();  
         }    
     }  
     
     
}
