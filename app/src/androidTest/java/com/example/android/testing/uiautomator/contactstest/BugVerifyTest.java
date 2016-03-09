package com.example.android.testing.uiautomator.contactstest;

/**
 * Created by betty on 6/30/15.
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * 1.back to home
 * 2.UiScrollable to pag 2
 * 3.press contacts
 * 4.press local contacts
 * 5.press menu
 * 6.press download
 * 7.UiSelector text download server
 * 8.clearTextField then setText("http://192.168.120.242/TEST_DATA/Contacts/GXP2200/contacts/1001_utf8.xml")
 * 9.press download
 * 10.back
 * 11.press contacts
 */
public class BugVerifyTest extends Unit {


    //bug1111
    public void bug1111(){


    }

    public void p1BugVerify(){


 }

    public void p2BugVerify(){


 }

    public void p3BugVerify(){


 }

 /**
 * verify the bug read from bug.xml
 **/

    public void bugListVerify(String url){
     try
     {   Scanner s = new Scanner( new File("/data/bug.txt") );
         while( s.hasNextInt() )
         {
             System.out.println( s.nextInt() );
             //java reflect

         }
     }
     catch(IOException e)
     {	System.out.println( e );

     }

 }

 public void bugListDownload(String bugUrl){
     String bugFileUrl = bugUrl;
     String pathName="/data/bug.txt";
     OutputStream output=null;
     try {
         URL url=new URL(bugFileUrl);
         HttpURLConnection conn=(HttpURLConnection)url.openConnection();
         File file=new File(pathName);
         InputStream input=conn.getInputStream();
         if(file.exists()){
             System.out.println("exits");
             return;
         }else{
             String dir="/data";
             new File(dir).mkdir();
             file.createNewFile();
             output=new FileOutputStream(file);
             byte[] buffer=new byte[4*1024];
             while(input.read(buffer)!=-1){
                 output.write(buffer);
             }
             output.flush();
         }
     } catch (Exception e) {
         e.printStackTrace();
     } finally{
         try {
             output.close();
             System.out.println("success");
         } catch (Exception e) {
             System.out.println("fail");
             e.printStackTrace();
         }
     }




 }




}