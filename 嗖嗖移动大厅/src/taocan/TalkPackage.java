package taocan;
 
import ���ƶ�����.Common;
import jiekou.CallService;
import jiekou.SendService;
import ���ƶ�����.MobileCard;
 

public class TalkPackage extends ServicePackage implements CallService,SendService {
	//ͨ��ʱ��
	private int talkTime;
	//��������
	private int smsCount;
	//�ײ����ʷ�
	private double price;
	
	public TalkPackage() {
		this.talkTime = 500;
		this.smsCount = 30;
		this.price=58;
	}

	public int getTalkTime() {
		return talkTime;
	}

	public void setTalkTime(int talkTime) {
		this.talkTime = talkTime;
	}

	public int getSmsCount() {
		return smsCount;
	}

	public void setSmsCount(int smsCount) {
		this.smsCount = smsCount;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void showInfo() {
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ��" + this.getTalkTime() + "����/��,��������Ϊ��" 
				+ this.getSmsCount() + "��/��,�ʷ�Ϊ��"
				+ this.getPrice() + "Ԫ/��" );
	}
	
	@Override
	public int send(int count, MobileCard card) throws Exception {
		 int temp = count;
         for(int i = 0; i < count; i++){
             if(this.smsCount - card.getRealSMSCount() >= 1){
                 card.setRealSMSCount(card.getRealSMSCount() + 1);
             }else if(card.getMoney() >= 0.1){
                 card.setRealSMSCount(card.getRealSMSCount() + 1); 
                 card.setMoney(card.getMoney() - 0.1);
                 card.setConsumAmout(card.getConsumAmout() + 0.1);  
             }else{
                 temp = i; 
                 throw new Exception("�����Ѿ�����" + i + "�����������㣬���ֵ����ʹ�ã�");             }
         }
         return temp;
	}
	@Override
	public int call(int minCount, MobileCard card) throws Exception{
		int temp=minCount;
		for (int i = 0; i < minCount; i++) {
			if (this.talkTime-card.getRealTakTime()>=1) {
				card.setRealTakTime(card.getRealTakTime()+1);  
			}else if (card.getMoney()>=0.2) {
				card.setRealTakTime(card.getRealTakTime()+1); 
				card.setMoney(Common.sub(card.getMoney(),0.2));
				card.setConsumAmout(card.getConsumAmout()+0.2);
			}else {
				temp=i;
				throw new Exception("����ͨ��"+i+"���ӣ��������㣬���ֵ����ʹ�ã�");
			}
		}
		return temp;
	}
}