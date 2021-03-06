package com.wanhao.proback.utils;

import net.sf.jmimemagic.*;

/**
 * Created by LiuLiHao on 2018/7/25 10:51.
 * 描述： 文件检查
 * 作者： LiuLiHao
 */
public class FileCheckUtil {
    private static Magic parser = new Magic() ;

    public static String getFileMimeType(byte[] bytes){
        MagicMatch match = null;
        try {
            match = parser.getMagicMatch(bytes);
        } catch (MagicParseException e) {
            e.printStackTrace();
        } catch (MagicMatchNotFoundException e) {
            e.printStackTrace();
        } catch (MagicException e) {
            e.printStackTrace();
        }
        return match.getMimeType();
    }

}
