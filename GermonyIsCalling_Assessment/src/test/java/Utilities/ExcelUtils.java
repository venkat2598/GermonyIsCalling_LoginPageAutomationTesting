package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {

	static FileInputStream excel;
	static Workbook workbook;


		public static String[][] readDataFromExcel()  {
			try {
				excel=new FileInputStream("./ExcelLoginData/LoginPageUserData_GermonyIsCalling.xlsx");
				workbook=new XSSFWorkbook(excel);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			Sheet sheet=workbook.getSheetAt(0);
			int lastrowcount=sheet.getLastRowNum();
			int lastcolumncount=sheet.getRow(0).getLastCellNum();  //based on the header title get the column count
			String [][] Userdata=new String[lastrowcount][lastcolumncount];
			for (int i = 1; i <=lastrowcount; i++) {   //  skip the header row and start from the actual data iterate until come lastrow
				Row rowvalue = sheet.getRow(i);
				for (int j = 0; j < lastcolumncount; j++) {   //iterate until come lastcolumn
					Cell columnvalue = rowvalue.getCell(j);
					DataFormatter formatter=new DataFormatter();  //used for format any datatype convert into string datatype.
					String values=	formatter.formatCellValue(columnvalue);
					//System.out.println(values);
					Userdata[i-1][j]=values;    //assign those values into the userdata variable.		}
				}
			}

			try {
				workbook.close();  //close  the workbook after reading the data from excel which is used for security purpose.
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Userdata;


		}
	

}
