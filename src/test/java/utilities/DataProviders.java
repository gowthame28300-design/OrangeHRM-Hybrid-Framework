package utilities;

import java.nio.file.Paths;

import org.testng.annotations.DataProvider;

public class DataProviders {

    private static final String SHEET_NAME = "LoginData";

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws Exception {

        String filePath = Paths.get(
                System.getProperty("user.dir"),
                "TestData",
                "OrangeHRM.xlsx"
        ).toString();

        ExcelUtility excel = new ExcelUtility(filePath);

        int totalRows = excel.getRowCount(SHEET_NAME);
        int totalColumns = excel.getCellCount(SHEET_NAME, totalRows);

        Object[][] loginData = new Object[totalRows][totalColumns];

        for (int row = 1; row <= totalRows; row++) {

            for (int column = 0; column < totalColumns; column++) {

                loginData[row - 1][column] =
                        excel.getCellData(SHEET_NAME, row, column);
            }
        }

        return loginData;
    }
}