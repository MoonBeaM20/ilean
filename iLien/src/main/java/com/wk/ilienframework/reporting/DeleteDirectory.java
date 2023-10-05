package com.wk.ilienframework.reporting;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

public class DeleteDirectory {
public static void main(String[] args) {
	File directory = new File("\\\\C0040670\\Software2\\SharePath\\SanityReportForConsolidation");
	 String folder = "\\\\C0040670\\Software2\\SharePath\\SanityReportForConsolidation";
     //delete folder recursively
	 try {
		FileUtils.cleanDirectory(directory);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
   //  recursiveDelete(new File(folder));
 }
 
 public static void recursiveDelete(File file) {
     //to end the recursive loop
     if (!file.exists())
         return;
     
     //if directory, go inside and call recursively
     if (file.isDirectory()) {
    	
         for (File f : file.listFiles()) {
             //call recursively
             recursiveDelete(f);
         }
     }
     //call delete to delete files and empty directory
    
     file.delete();
     
     System.out.println("Deleted file/folder: "+file.getAbsolutePath());
 }
	
	

}
