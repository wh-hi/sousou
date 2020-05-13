package à²à²ÒÆ¶¯´óÌü;
 
import java.text.DecimalFormat;
 
public class Common {

	public static String dataFormat(double data) {
		DecimalFormat format=new DecimalFormat("#0.00");
		return format.format(data);
	}

	public static double sub(double sum1, double sum2) {
		return (sum1-sum2);
	}
 
}
