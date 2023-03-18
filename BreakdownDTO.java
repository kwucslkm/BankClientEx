package BankClientUp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author seolne
 *
 */
public class BreakdownDTO {
	private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yy/MM/dd hh:mm:ss");
	private static Integer number = 100;
	private String dealNo;
	private String account;
	private String division;
	private long dealMoney;
	private long totalMoney;
	private String dealDate;

	public BreakdownDTO() {
		this.dealDate = DTF.format(LocalDateTime.now());

		this.dealNo = "B" + number++;
	}
	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public String getDealNo() {
		return dealNo;
	}

	public void setDealNo(String dealNo) {
		this.dealNo = dealNo;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public long getDealMoney() {
		return dealMoney;
	}
	public void setDealMoney(long dealMoney) {
		this.dealMoney = dealMoney;
	}
	public long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(long totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Override
	public String toString() {
		String str = account + "\t" + division + "\t" + dealMoney + "\t" + totalMoney + "\t" + dealDate;
		return str;
	}
	public static Integer getNumber() {
		return number;
	}
	public static void setNumber(Integer number) {
		BreakdownDTO.number = number;
	}

}
