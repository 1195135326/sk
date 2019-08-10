package com.ts.common;

import com.ts.comm.SysJson;
import com.ts.comm.SysNumber;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Utils {
   public  static  String getSysPath()
   {
       String sPath = "";
       try{
           sPath = getPropertyValue("ServerFilePath");
       }catch (Exception e)
       {
            e.printStackTrace();
       }
       return sPath;
   }
    public  static  String getPropertyValue(String sKey)
    {
        String sValue = "";
        try{
            Properties properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = Utils.class.getClassLoader().getResourceAsStream("application-dev.properties");
            // 使用properties对象加载输入流
            properties.load(in);
            //获取key对应的value值
            sValue = properties.getProperty(sKey);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return sValue;
    }
}
