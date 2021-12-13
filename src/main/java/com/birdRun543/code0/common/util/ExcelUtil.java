package com.birdRun543.code0.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @author hanbing
 */
@Slf4j
public class ExcelUtil {

    private static HSSFWorkbook initWorkBook(Map<String, String> titleMap) {
        // 创建Excel的工作书册 Workbook,对应到一个excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建Excel的工作sheet,对应到一个excel文档的tab
        HSSFSheet sheet = workbook.createSheet("sheet1");
        // 创建Excel的sheet的一行
        HSSFRow row = sheet.createRow(0);
        // 创建一个Excel的单元格
        final HSSFCell[] cell = {row.createCell(0)};

        // 给Excel的单元格赋值
        final int[] i = {1};
        titleMap.forEach((k, v) -> {
            cell[0].setCellValue(v);
            cell[0] = row.createCell(i[0]++);
        });

        return workbook;
    }

    /**
     * 设置Excel 下载 header
     *
     * @param response HttpServletResponse
     * @param name     表格名称
     */
    public static void setResponseExportExcelHeader(HttpServletResponse response,
                                                    String name) throws UnsupportedEncodingException {
        String s = new String((name + ".xls").getBytes(), "ISO8859-1");
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + s);
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    }

    public static <T> HSSFWorkbook createExcel(Map<String, String> titleMap, List<T> recordList) {
        HSSFWorkbook workbook = initWorkBook(titleMap);

        HSSFSheet sheet = workbook.getSheet("sheet1");

        recordList.forEach(record -> {
            int rownum = recordList.indexOf(record) + 1;
            HSSFRow row = sheet.createRow(rownum);
            // 给Excel的单元格赋值
            final int[] j = {0};
            titleMap.forEach((k, v) -> {
                HSSFCell cell = row.createCell(j[0]++);
                String value = BasicItemUtil.getFieldValueByFieldName(k, record);
                if (isValidDate(value)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    try {
                        sdf.parse(value);
                    } catch (ParseException e) {
                        log.debug(e.getMessage(), e);
                    }

                }
                cell.setCellValue(BasicItemUtil.getFieldValueByFieldName(k, record));
            });
        });
        return workbook;
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            //设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
