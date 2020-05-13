package ���ƶ�����;

import taocan.ServicePackage;

public class MobileCard {
	// ����
	private String cardNumber;
	// �û���
	private String userName;
	//����
	private String passWord;
	//�����ײ�
	private ServicePackage serPackage;
	//�������ѽ��
	private double consumAmout;
	//�˻����
	private double money;
	//����ʵ��ͨ��ʱ��
	private int realTakTime;
	//����ʵ�ʷ��Ͷ�������
	private int realSMSCount;
	//����ʵ����������
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
		 System.out.println("����:"+this.getCardNumber()+"�û���:"+this.getUserName()+"��ǰ���:"+this.getMoney()+"Ԫ");
		 this.serPackage.showInfo();
	}
}