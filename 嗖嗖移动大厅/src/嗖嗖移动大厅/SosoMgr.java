package ���ƶ�����;
 
import java.util.Scanner;
 
import ���ƶ�����.MobileCard;
import taocan.ServicePackage;
import ���ƶ�����.CardUtil;

public class SosoMgr {
	Scanner input=new Scanner(System.in);
	CardUtil cardUtil=new CardUtil();
	MobileCard mobileCard=new MobileCard();
	ServicePackage service= null;
	
	public static void main(String[] args) {
		SosoMgr sosoMgr = new SosoMgr();
        sosoMgr.start();
	}
	
	public void start() {
		cardUtil.intitScene();
		boolean flag=true;
		do {
		System.out.println("**************��ӭʹ�����ƶ�ҵ�����***************");
		System.out.println("1.�û���¼  2.�û�ע��  3.ʹ����  4.���ѳ�ֵ  5.�ʷ�˵��  6.�˳�ϵͳ");
		System.out.print("��ѡ��");
		int key=0;
		try {
			key = input.nextInt();
		} catch (Exception e) {
			System.out.println("�����������������룡");
		}
		switch (key) {
		case 1:
			cardMenu();
			continue;
		case 2:
			registCard();
			continue;
		case 3:
			System.out.println("ִ��ʹ���ಹ���");
			System.out.println("�������ֻ�����:");
			String number=input.next();
			cardUtil.userSoso(number); 	
			continue;
		case 4:
			moneyRecharge();
			continue;
		case 5:
			cardUtil.showDescription();
			continue;
		case 6:
			System.out.println("ллʹ�ã�");
			break;
		default:
			System.out.println("�����������������룡");
			continue;
		}
	} while (flag);
}

	public void moneyRecharge() {
		System.out.println("�������ֻ����ţ�");
		String strNum=input.next();
			boolean b=cardUtil.isExistCard(strNum);
			if (b) {
			System.out.println("�������ֵ��");
			double strMoney=input.nextDouble();
				cardUtil.chargeMoney(strNum,strMoney);
				System.out.println("��ֵ�ɹ�,��ǰ�������Ϊ"+CardUtil.cards.get(strNum).getMoney()+"Ԫ");
			}else {
				System.out.println("�ֻ��������������������ֻ��ţ�");
		}
	}
	public void cardMenu() {
		System.out.println("�������ֻ����ţ�");
		String number=input.next();
		System.out.println("���������룺");
		String pwd=input.next();
		if (cardUtil.isExistCard(number,pwd)) {
			System.out.println("��¼�ɹ�");
		}else {
			return;
		}
		boolean flag=true; 
		do{
		System.out.println("******���ƶ��û��˵�******");
		System.out.println("1.�����˵���ѯ");
		System.out.println("2.�ײ�������ѯ");
		System.out.println("3.��ӡ�����嵥");
		System.out.println("4.�ײͱ��");
		System.out.println("5.��������");
		System.out.print("��ѡ��(����1~5ѡ����,������������һ��)��");
		int key=1;
		try {
			key = input.nextInt();
		} catch (Exception e) {
			new	SosoMgr().start();
		}
		switch (key) {
		case 1:
			cardUtil.showAmountDetail(number);
			continue;
		case 2:
			cardUtil.showRemainDetail(number);
			continue;
		case 3:
			cardUtil.printAmountDetail(number);
			continue;
		case 4:
			cardUtil.chargeingPack(number);
			continue;
		case 5:
			cardUtil.delCard(number);	
			continue;
		default:
			flag = false;
			new SosoMgr().start();
			}
		} while(flag); 
	}
 
	public void registCard() {
		System.out.println("**********��ѡ��Ŀ���**********");
		String[] cardNumbers = cardUtil.getNewNumber(9);
		for (int i = 0; i <cardNumbers.length; i++) {
			System.out.print((i+1)+":"+cardNumbers[i]+"\n");
		}
		System.out.println("��ѡ�񿨺�(����1~9�����)��");
		int key = input.nextInt();
		
		if (key >= 1 && key <= 9) {
			mobileCard.setCardNumber(cardNumbers[key - 1]);
			System.out.println("1.�����ײ�  2.�����ײ�  3.�����ײ� �� ��ѡ���ײͣ�������ţ�:");
		} else {
			System.out.print("������������루1~9��������:");
		}
		
		boolean bol = true;
	
		int packageNum = input.nextInt();
		while (bol) {
			if (packageNum<=3 && packageNum>=1) {
				service= cardUtil.createPackage(packageNum);
				  mobileCard.setSerPackage(service);  //
	            bol = false;
			} else {
				System.out.println("�������������ѡ��");
				packageNum = input.nextInt();
			}
		}
		
        System.out.println("������������");
        String userName =  input.next();
        System.out.println("���������룺");
        String passWord = input.next();
        System.out.println("������Ԥ�滰�ѣ�");
        double money=input.nextInt();
        while (money<service.getPrice()) {
			System.out.println("��Ԥ�滰�ѽ�����֧�����¹̶��ײ��ʷѣ������³�ֵ��");
			money=input.nextInt();
       }
        mobileCard.setMoney(money-service.getPrice());
        MobileCard card=new MobileCard(mobileCard.getCardNumber(), userName, passWord,mobileCard.getSerPackage(),mobileCard.getMoney()); 
        cardUtil.addCard(card);
        card.showMeg();
	}
}
