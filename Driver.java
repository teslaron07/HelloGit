import java.util.Arrays;
import java.util.List;

public class Driver {

	public static long starttime = 0;
	public static long endtime = 0;

	public static void main(String[] args) throws Exception {

		starttime = System.currentTimeMillis();
		XExcelFileReader excl = new XExcelFileReader("D:\\Data.xlsx");
		List<String[]> mydata1 = excl.readRows(200000);
		for (String[] mydata2 : mydata1) {
			String ACCOUNT_ID = mydata2[0];
			String AVAIL_BALANCE = mydata2[1];
			String CLOSE_DATE = mydata2[2];
			String LAST_ACTIVITY_DATE = mydata2[3];
			String PENDING_BALANCE = mydata2[4];
			String STATUS = mydata2[5];
			String CUST_ID = mydata2[6];
			String OPEN_BRANCH_ID = mydata2[7];
			String OPEN_EMP_ID = mydata2[8];
			String PRODUCT_CD = mydata2[9];
			String TXN_ID = mydata2[10];
			String AMOUNT = mydata2[11];
			String FUNDS_AVAIL_DATE = mydata2[12];
			String TXN_DATE = mydata2[13];
			String TXN_TYPE_CD = mydata2[14];
			String ACCOUNT_ID_1 = mydata2[15];
			String TELLER_EMP_ID = mydata2[16];
			String ADDRESS = mydata2[17];
			String CITY = mydata2[18];
			String NAME = mydata2[19];
			String STATE = mydata2[20];
			String ZIP_CODE = mydata2[21];
			String INCORP_DATE = mydata2[22];
			String NAME_1 = mydata2[23];
			String STATE_ID = mydata2[24];
			String CUST_ID_1 = mydata2[25];
			String CUST_ID_2 = mydata2[26];
			String ADDRESS_1 = mydata2[27];
			String CITY_1 = mydata2[28];
			String CUST_TYPE_CD = mydata2[29];
			String FED_ID = mydata2[30];
			String POSTAL_CODE = mydata2[31];
			String STATE_1 = mydata2[32];
			String DEPT_ID = mydata2[33];
			String NAME_2 = mydata2[34];
			String EMP_ID = mydata2[35];
			String FIRST_NAME = mydata2[36];
			String LAST_NAME = mydata2[37];
			String START_DATE = mydata2[38];
			String TITLE = mydata2[39];
			String ASSIGNED_BRANCH_ID = mydata2[40];
			String DEPT_ID_1 = mydata2[41];
			String BIRTH_DATE = mydata2[42];
			String FIRST_NAME_1 = mydata2[43];
			String LAST_NAME_1 = mydata2[44];
			String CUST_ID_3 = mydata2[45];
			String OFFICER_ID = mydata2[46];
			String END_DATE_1 = mydata2[47];
			String FIRST_NAME_2 = mydata2[48];
			String LAST_NAME_2 = mydata2[49];
			String START_DATE_1 = mydata2[50];
			String TITLE_1 = mydata2[51];
			String CUST_ID_4 = mydata2[52];
			String PRODUCT_CD_1 = mydata2[53];
			String DATE_RETIRED = mydata2[54];
			String NAME_3 = mydata2[55];
			String PRODUCT_TYPE_CD = mydata2[56];
			String PRODUCT_TYPE_CD_1 = mydata2[57];

			System.out.print(ACCOUNT_ID + AVAIL_BALANCE + CLOSE_DATE + LAST_ACTIVITY_DATE + PENDING_BALANCE + STATUS
					+ CUST_ID + OPEN_BRANCH_ID + OPEN_EMP_ID + PRODUCT_CD + TXN_ID + AMOUNT + FUNDS_AVAIL_DATE
					+ TXN_DATE + TXN_TYPE_CD + ACCOUNT_ID_1 + TELLER_EMP_ID + ADDRESS + CITY + NAME + STATE + ZIP_CODE
					+ INCORP_DATE + NAME_1 + STATE_ID + CUST_ID_1 + CUST_ID_2 + ADDRESS_1 + CITY_1 + CUST_TYPE_CD
					+ FED_ID + POSTAL_CODE + STATE_1 + DEPT_ID + NAME_2 + EMP_ID  + FIRST_NAME + LAST_NAME
					+ START_DATE + TITLE + ASSIGNED_BRANCH_ID + DEPT_ID_1 + BIRTH_DATE + FIRST_NAME_1 + LAST_NAME_1
					+ CUST_ID_3 + OFFICER_ID + END_DATE_1 + FIRST_NAME_2 + LAST_NAME_2 + START_DATE_1 + TITLE_1
					+ CUST_ID_4 + PRODUCT_CD_1 + DATE_RETIRED + NAME_3 + PRODUCT_TYPE_CD + PRODUCT_TYPE_CD_1);
			System.out.println();
		}
		endtime = System.currentTimeMillis();
		System.out.println("TimeTaken :" + (endtime - starttime));

	}

}
