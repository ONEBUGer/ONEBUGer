package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class HugeExcelExportTest {

    private int totalRowNumber = 1000000; //写入的excel数据行数
    private int totalCellNumber = 40; //excel每行共40列

    //普通的写入excel的方法，会消耗内存，写入的行数太大时，会报内存溢出
    @Test
    public void writeNormalExcelTest(){

        Workbook wb = null;
        FileOutputStream out = null;

        try {

            long startTime = System.currentTimeMillis();

            wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Sheet 1");

            //定义Row和Cell变量, Rows从0开始.
            Row row;
            Cell cell;

            for (int rowNumber = 0; rowNumber < totalRowNumber; rowNumber++) {
                row = sheet.createRow(rowNumber);
                for (int cellNumber = 0; cellNumber < totalCellNumber; cellNumber++) {
                    cell = row.createCell(cellNumber);
                    cell.setCellValue(Math.random()); //写入一个随机数
                }

                //打印测试，
                if(rowNumber % 10000 ==0) {
                    System.out.println(rowNumber);
                }
            }

            //Write excel to a file
            out = new FileOutputStream("d:\\temp\\normalExcel_" + totalRowNumber + ".xlsx");
            wb.write(out);
            long endTime = System.currentTimeMillis();

            System.out.println("process " + totalRowNumber + " spent time:" + (endTime - startTime) + " ms.");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {


            try {
                if(out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(wb != null) wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //结合临时文件压缩等写入excel，默认超过100行就写到临时文件，不会报内存溢出
    @Test
    public void writeHugeExcelTest(){

        SXSSFWorkbook wb = null;
        FileOutputStream out = null;

        try {

            long startTime = System.currentTimeMillis();

            wb = new SXSSFWorkbook();//默认100行，超100行将写入临时文件
            wb.setCompressTempFiles(false); //是否压缩临时文件，否则写入速度更快，但更占磁盘，但程序最后是会将临时文件删掉的
            Sheet sheet = wb.createSheet("Sheet 1");

            //定义Row和Cell变量, Rows从0开始.
            Row row;
            Cell cell;

            for (int rowNumber = 0; rowNumber < totalRowNumber; rowNumber++) {

                row = sheet.createRow(rowNumber);
                for (int cellNumber = 0; cellNumber < totalCellNumber; cellNumber++) {
                    cell = row.createCell(cellNumber);
                    cell.setCellValue(Math.random()); //写入一个随机数
                }


                //打印测试，
                if(rowNumber % 10000 ==0) {
                    System.out.println(rowNumber);
                }

            }

            //Write excel to a file
            out = new FileOutputStream("d:\\temp\\hugeExcel_" + totalRowNumber + ".xlsx");
            wb.write(out);

            long endTime = System.currentTimeMillis();

            System.out.println("process " + totalRowNumber + " spent time:" + (endTime - startTime) + " ms.");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (wb != null) {
                wb.dispose();// 删除临时文件，很重要，否则磁盘可能会被写满
            }

            try {
                if(out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(wb != null) wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Test
    public void Test(){
        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println(replace);
    }

}