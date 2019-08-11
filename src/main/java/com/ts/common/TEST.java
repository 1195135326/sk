package com.ts.common;

import com.ts.comm.SysNumber;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by slq on 2019/8/10.
 */
public class TEST {

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

    public static boolean uploadFile(InputStream in , String sPath)  throws Exception {
        boolean bSuccess = false;

        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(getPropertyValue("ftp.url"), SysNumber.toInt(getPropertyValue("ftp.port")));
            ftpClient.login(getPropertyValue("ftp.url"), getPropertyValue("ftp.url"));
            int replyCode = ftpClient.getReplyCode();
            ftpClient.setDataTimeout(120000);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置为二进制文件

            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                bSuccess = false;
            } else {
                bSuccess = true;
            }
            //远程文件名
            String removePath = new String(sPath.getBytes("UTF-8"), "iso-8859-1");
            //上传
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            if (ftpClient.storeFile(removePath, in)) {
                bSuccess = true;
            } else {
                bSuccess = false;
            }
            //关闭文件流
            in.close();
            //关闭连接
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
            return  bSuccess;
        }catch (Exception e)
        {
            throw  e;
        }
    }

    public boolean downLoadFile(String sRemotePath) throws Exception{
        boolean bSuccess = false;
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(getPropertyValue("ftp.url"), SysNumber.toInt(getPropertyValue("ftp.port")));
            ftpClient.login(getPropertyValue("ftp.url"), getPropertyValue("ftp.url"));
            int replyCode = ftpClient.getReplyCode();
            ftpClient.setDataTimeout(120000);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置为二进制文件
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                bSuccess = false;
            } else {
                bSuccess = true;
            }
            //同理，假如指定不存在的路径，会去根路径下查找
//        ftpClient.changeWorkingDirectory("test2");
            File file=new File("E://abc4.jpg");
            FileOutputStream fos=new FileOutputStream(file);
            boolean result = ftpClient.retrieveFile(sRemotePath,fos);
            if(result) {
                bSuccess = true;
            }else {
                bSuccess = false;
            }
            //关闭文件流
            fos.close();
            //关闭连接
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
            return bSuccess;
        }catch (Exception e){
            throw e;
        }

    }
    public static void main(String[] args) {
        try {
            File file = new File("E://1.jpg");
            FileInputStream in = new FileInputStream(file);
            uploadFile(in, "E://ServerFile//test.jpg");


        } catch (Exception e) {

        }
    }




}




