package dataProviders;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
@DataProvider (name = "parkingData")
public String[][] parkingData(){
	
String[][]data={

{"0", "2024-10-30","2024-10-31","09:30", "09:30","18.00"},           
{"1", "2024-10-30","2024-10-31","09:30", "09:30","24.00"},
{"2", "2024-10-30","2024-10-31","09:30", "09:30","12.00"},
{"3", "2024-10-30","2024-10-31","09:30", "09:30","10.00"}
};

return data;
}



}
