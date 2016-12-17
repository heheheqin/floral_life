package com.dream.will.floral_life.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class SDUtils {
    /**
     * 保存文件到SD卡中
     *
     * @param in       网络文件输入流
     * @param fileName 要保存的文件名
     * @param filePath 文件保存在哪个目录,不包含SD卡跟路径
     */
    public static void saveFile(InputStream in, String fileName, String filePath)  {
        //先判断SD卡是否可用
        File file = getlegality(filePath);
        //创建文件
        File newFile = new File(file, fileName);
        OutputStream out = null;
        try {
            out = new FileOutputStream(newFile);
            byte[] buffer = new byte[2048];
            int tmp = 0;
            while ((tmp = in.read(buffer)) != -1) {
                out.write(buffer, 0, tmp);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out!=null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    /**
     * 保存文件到SD卡中
     *
     * @param arr      保存的字节数组
     * @param fileName 要保存的文件名
     * @param filePath 文件保存在哪个目录,不包含SD卡跟路径
     * @return 返回保存后的文件名及路径
     * @throws Exception
     */
    public static String saveFile(byte[] arr, String fileName, String filePath) {
        //先判断合法性
        File file = getlegality(filePath);
        if (file == null) return null;
        //创建文件
        File newFile = new File(file, fileName);
        String newPath = newFile.getAbsolutePath();
        OutputStream out = null;
        try {
            out = new FileOutputStream(newFile);
            out.write(arr, 0, arr.length);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return newPath;
    }

    public static  String saveFile(Bitmap bitmap,String filePath,String fileName){
        //先判断合法性
        File file = getlegality(filePath);
        if (file == null) return null;
        //创建文件
        File newFile = new File(file, fileName);
        //3.保存裁剪过的图片
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(newFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream !=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return newFile.getAbsolutePath();
    }

    @Nullable
    private static File getlegality(String filePath) {
        if (!isMount()) {
            Log.e("TAG", "================SD卡不可用===========");
            return null;
        }
        String path;
        //获取SDcart路径
        if (filePath == null) {
            //保存到SDcard根目录
            path = getSDcardPath();
        } else {
            path = getSDcardPath() + filePath;
        }
        //创建文件
        File file = new File(path);
        //如果目录文件不存在，则创建一个
        if (!file.exists()) {
            file.mkdir();
        } else {
            //目录文件已经存在，但如果不是目录，则直接返回
            if (!file.isDirectory()) {
                Log.e("TAG", "============保存的路径不是有效的目录============");
                return null;
            }
        }
        return file;
    }

    /**
     * 判断SD卡是否挂载
     *
     * @return
     */
    public static boolean isMount() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径，后面加上分隔符File.separator:/
     *
     * @return
     */
    public static String getSDcardPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + File.separator;
    }


    /**  从文件从获取字符串
     * @param string
     * @return
     */
    public  static  String getStringToFile(String string){

        BufferedReader bufferedReader= null;
        try {
            bufferedReader = new  BufferedReader(new InputStreamReader(new FileInputStream(string)));
            String read = null;
            StringBuilder stringBuilder = new StringBuilder();
            if ((read = bufferedReader.readLine()) != null){
                stringBuilder.append(read);
            }
            return  stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;

    }


}
