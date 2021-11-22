package date;
import java.util.Calendar;

public class MyDate {
	private int day;
	private int month;
	private int year;
	private boolean valid = true;
	
	public MyDate(int day, int month, int year) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	
	public String isValid() {
		if(valid)
			return "유효한 날짜입니다.";
		else
			return "유효하지 않은 날짜입니다.";
	}
	
	//DAY
	public void setDay(int day) {
		switch(month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				if (day <0 || day >31) 
					this.valid = false;
				else 
					this.day = day;
				break;
			case 4: case 6: case 9: case 11:
				if (day <0 || day >30)
					valid = false;
				else 
					this.day = day;
				break;
			case 2:
				if (( ( year % 4 ==0 &&  year % 100 !=0 ) || year % 400 ==0)){  //윤년인 경우
					if (day <0 || day >29) 
						this.valid = false;
					else
						this.day = day;
				}
				else {
					if (day <0 || day >28) 
						this.valid = false;
					else
						this.day = day;
				}
				break;
			default:
				this.valid = false;
		}
	}
	public int getDay() {
		return day;
	}
	
	//MONTH
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		if(month<1 || month>12) 
			valid = false;
		else
			this.month = month;
	}
	
	//YEAR
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		if (year > Calendar.getInstance().get(Calendar.YEAR)) {
			this.valid = false;
		}
		else {
			this.year = year;
		}
	}
	
}
