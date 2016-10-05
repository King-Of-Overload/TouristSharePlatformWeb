package alan.share.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipImagesUtil {
	
	public static String getZipFilename(){
		Date date=new Date();
		String result=date.getTime()+".zip";
		return result;
	}
	
	public static void zipFile(File[] subs,String baseName,ZipOutputStream zos,File picDir) throws IOException{
		for(int i=0;i<subs.length;i++){
			File f=subs[i];
			ZipEntry entry=new ZipEntry(baseName+f.getName());
			zos.putNextEntry(new ZipEntry(entry));
			FileInputStream fis=new FileInputStream(f);
			byte[] buffer=new byte[1024];
			int length=0;
			while((length=fis.read(buffer))!=-1){
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
			fis.close();
		}
		
		deleteFile(picDir);
	}
	
	
	public static void deleteFile(File file){
		if(file.exists()){//判断文件是否存在
			if(file.isFile()){//是否是文件
				file.delete();
			}else if(file.isDirectory()){//如果是目录
				File files[]=file.listFiles();
				for(int i=0;i<files.length;i++){
					deleteFile(files[i]);//递归删除
				}
			}
			file.delete();
		}else{
			System.out.println("所删除的文件不存在"+'\n');
		}
	}
	

}
