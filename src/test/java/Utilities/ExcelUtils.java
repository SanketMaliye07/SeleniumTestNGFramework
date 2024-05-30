package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {
	
    private static final String EXCEL_PATH = "src/main/java/TestData/LoginData.xlsx";

    public static String readFromExcel(int rowNumber, int cellNumber) {
        try (FileInputStream file = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook wb = new XSSFWorkbook(file)) {

            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.getRow(rowNumber);

            if (row != null) {
                XSSFCell cell = row.getCell(cellNumber);

                if (cell != null) {
                    cell.setCellType(CellType.STRING); // Ensure that the cell type is String
                    return cell.toString();
                } else {
                    System.out.println("Cell at column " + cellNumber + " is null.");
                }
            } else {
                System.out.println("Row at row number " + rowNumber + " is null.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from Excel: " + e.getMessage());
        }

        return null;
    }

    public static void writeToExcel(int rowNumber, int cellNumber, String value) {
        try (FileInputStream file = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook wb = new XSSFWorkbook(file);
             FileOutputStream outFile = new FileOutputStream(EXCEL_PATH)) {

            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.getRow(rowNumber);

            if (row == null) {
                row = sheet.createRow(rowNumber);
            }

            XSSFCell cell = row.getCell(cellNumber);

            if (cell == null) {
                cell = row.createCell(cellNumber);
            }

            cell.setCellValue(value);
            wb.write(outFile);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing to Excel: " + e.getMessage());
        }
    }

    private static int getRowCount() {
        try (FileInputStream file = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook wb = new XSSFWorkbook(file)) {

            XSSFSheet sheet = wb.getSheetAt(0);
            return sheet.getPhysicalNumberOfRows();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error getting row count from Excel: " + e.getMessage());
        }

        return 0;
    }

    private static int getColumnCount() {
        try (FileInputStream file = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook wb = new XSSFWorkbook(file)) {

            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.getRow(0);

            return (row != null) ? row.getPhysicalNumberOfCells() : 0;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error getting column count from Excel: " + e.getMessage());
        }

        return 0;
    }

    public static void printRow(int rowNumber) {
        try (FileInputStream file = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook wb = new XSSFWorkbook(file)) {

            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row = sheet.getRow(rowNumber);

            if (row != null) {
                int lastCellNum = row.getLastCellNum();
                for (int i = 0; i < lastCellNum; i++) {
                    XSSFCell cell = row.getCell(i);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        System.out.print(cell.toString() + "\t");
                    }
                }
                System.out.println();
            } else {
                System.out.println("Row at row number " + rowNumber + " is null.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error printing row from Excel: " + e.getMessage());
        }
    }

    public static void printColumn(int columnNumber) {
        try (FileInputStream file = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook wb = new XSSFWorkbook(file)) {

            XSSFSheet sheet = wb.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    XSSFCell cell = row.getCell(columnNumber);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        System.out.println(cell.toString());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error printing column from Excel: " + e.getMessage());
        }
    }

     
}
