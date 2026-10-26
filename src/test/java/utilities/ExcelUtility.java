package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private final String path;

    public ExcelUtility(String path) {
        this.path = path;
    }

    // Get the number of data rows, excluding the header row
    public int getRowCount(String sheetName) throws IOException {

        try (FileInputStream input = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            XSSFSheet sheet = getRequiredSheet(workbook, sheetName);

            return Math.max(0, sheet.getLastRowNum());
        }
    }

    // Get the number of cells in a row
    public int getCellCount(String sheetName, int rownum)
            throws IOException {

        try (FileInputStream input = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            XSSFSheet sheet = getRequiredSheet(workbook, sheetName);
            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                return 0;
            }

            return Math.max(0, row.getLastCellNum());
        }
    }

    // Get cell data as a formatted string
    public String getCellData(String sheetName, int rownum, int column)
            throws IOException {

        try (FileInputStream input = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            XSSFSheet sheet = getRequiredSheet(workbook, sheetName);
            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                return "";
            }

            Cell cell = row.getCell(column);

            if (cell == null) {
                return "";
            }

            DataFormatter formatter = new DataFormatter();

            return formatter.formatCellValue(cell);
        }
    }

    // Set cell data
    public void setCellData(
            String sheetName,
            int rownum,
            int column,
            String data) throws IOException {

        Path filePath = Paths.get(path);

        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }

        if (!Files.exists(filePath)) {

            try (XSSFWorkbook workbook = new XSSFWorkbook()) {

                workbook.createSheet(sheetName);

                try (FileOutputStream output =
                             new FileOutputStream(path)) {

                    workbook.write(output);
                }
            }
        }

        try (FileInputStream input = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                row = sheet.createRow(rownum);
            }

            Cell cell = row.getCell(column);

            if (cell == null) {
                cell = row.createCell(column);
            }

            cell.setCellValue(data);

            try (FileOutputStream output =
                         new FileOutputStream(path)) {

                workbook.write(output);
            }
        }
    }

    // Fill a cell with a specified color
    public void fillGreenColor(
            String sheetName,
            int rownum,
            int column) throws IOException {

        fillCellColor(
                sheetName,
                rownum,
                column,
                IndexedColors.GREEN
        );
    }

    // Fill a cell with a specified color
    public void fillRedColor(
            String sheetName,
            int rownum,
            int column) throws IOException {

        fillCellColor(
                sheetName,
                rownum,
                column,
                IndexedColors.RED
        );
    }

    // Shared implementation for cell coloring
    private void fillCellColor(
            String sheetName,
            int rownum,
            int column,
            IndexedColors color) throws IOException {

        try (FileInputStream input = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(input)) {

            XSSFSheet sheet = getRequiredSheet(workbook, sheetName);

            XSSFRow row = sheet.getRow(rownum);

            if (row == null) {
                throw new IllegalArgumentException(
                        "Row " + rownum + " does not exist in sheet "
                                + sheetName);
            }

            Cell cell = row.getCell(column);

            if (cell == null) {
                throw new IllegalArgumentException(
                        "Cell at row " + rownum
                                + ", column " + column
                                + " does not exist");
            }

            CellStyle style = workbook.createCellStyle();

            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);

            try (FileOutputStream output =
                         new FileOutputStream(path)) {

                workbook.write(output);
            }
        }
    }

    // Validate and retrieve a worksheet
    private XSSFSheet getRequiredSheet(
            XSSFWorkbook workbook,
            String sheetName) {

        XSSFSheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new IllegalArgumentException(
                    "Excel sheet not found: " + sheetName);
        }

        return sheet;
    }
}