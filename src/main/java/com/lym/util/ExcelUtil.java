package com.lym.util;

import com.google.common.base.Strings;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/7/9 14:25
 * @Description:
 */
public class ExcelUtil {

    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb) {

        //第一步 创建一个HSSFWorkbook对象,对应一个excel文件
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        //第二步 在HSSFWorkbook中创建一个sheet 对应Excel中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        //第三步 在sheet中添加表头第0行 注意老版本的poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        //第四步 创建单元格,并设置表头居中
        HSSFCellStyle hssfCellStyle = wb.createCellStyle();
        hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        //普通单元格居中
        HSSFCellStyle hssfCellStyle2 = wb.createCellStyle();
        hssfCellStyle2.setAlignment(HorizontalAlignment.CENTER);

        //标题加粗
        HSSFFont font = wb.createFont();
        font.setBold(true);
        hssfCellStyle.setFont(font);

        //第五步 创建列对象
        HSSFCell cell = null;

        //第六步 创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(hssfCellStyle);
        }

        //第七步 创建内容
        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋值给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(values[i][j]);
                cell.setCellStyle(hssfCellStyle2);
            }
        }


        return wb;
    }
}
