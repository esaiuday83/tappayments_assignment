package com.tap.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtils {

    // This method will convert clipboard text as String
    public static String copyClipboardContents() throws UnsupportedFlavorException, IOException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        return (String) clipboard.getData(DataFlavor.stringFlavor);
    }

    public static String getHostName(){
        InetAddress inetAddress = null;
        try{
            inetAddress = InetAddress.getLocalHost();
        }catch(UnknownHostException uke){
            uke.printStackTrace();
        }
        assert inetAddress != null;
        return inetAddress.getHostName();
    }

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + date + ".png";
        File fileDestination = new File(destination);
        try {
            FileUtils.copyFile(source, fileDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

}
