package 嗖嗖移动大厅;

import taocan.ServicePackage;

public class MobileCard {
	// 卡号
	private String cardNumber;
	// 用户名
	private String userName;
	//密码
	private String passWord;
	//所属套餐
	private ServicePackage serPackage;
	//当月消费金额
	private double consumAmout;
	//账户余额
	private double money;
	//当月实际通话时长
	private int realTakTime;
	//当月实际发送短信条数
	private int realSMSCount;
	//当月实际上网流量
	private int realFlow;
 
	public MobileCard(){}

	public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage,
			double consumAmout, double money, int realTakTime, int realSMSCount, int realFlow) {
		
		this.cardNumber = cardNumber;
		this.userName = userName;
		this.passWord = passWord;
		this.serPackage = serPackage;
		this.consumAmout = consumAmout;
		this.money = money;
		this.realTakTime = realTakTime;
		this.realSMSCount = realSMSCount;
		this.realFlow = realFlow;
	}
	
	public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage, double money){
		this.cardNumber = cardNumber;
		this.userName = userName;
		this.passWord = passWord;
		this.serPackage = serPackage;
		this.money = money;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public ServicePackage getSerPackage() {
		return serPackage;
	}

	public void setSerPackage(ServicePackage serPackage) {
		this.serPackage = serPackage;
	}
	
	public double getConsumAmout() {
		return consumAmout;
	}
	
	public void setConsumAmout(double consumAmout) {
		this.consumAmout = consumAmout;
	}
	
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	public int getRealTakTime() {
		return realTakTime;
	}
	
	public void setRealTakTime(int realTakTime) {
		this.realTakTime = realTakTime;
	}
	
	public int getRealSMSCount() {
		return realSMSCount;
	}
	
	public void setRealSMSCount(int realSMSCount) {
		this.realSMSCount = realSMSCount;
	}
	
	public int getRealFlow() {
		return realFlow;
	}
	
	public void setRealFlow(int realFlow) {
		this.realFlow = realFlow;
	}
	
	public void showMeg() {
		 System.out.println("卡号:"+this.getCardNumber()+"用户名:"+this.getUserName()+"当前余额:"+this.getMoney()+"元");
		 this.serPackage.showInfo();
	}
}