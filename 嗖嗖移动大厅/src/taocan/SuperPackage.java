package taocan;
 
import jiekou.CallService;
import jiekou.NetService;
import jiekou.SendService;
import ���ƶ�����.MobileCard;
 

public class SuperPackage extends ServicePackage implements CallService,SendService,NetService{
	//ͨ��ʱ��
	private int talkTime;
	//��������
	private int smsCount;
	//��������
	private int flow;
	//�ײ����ʷ�
	private double price;
	
	public SuperPackage() {
		this.talkTime = 200;
		this.smsCount = 50;
		this.flow = 1;
		this.price=78;
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
	
	public int getFlow() {
		return flow;
	}
	
	public void setFlow(int flow) {
		this.flow = flow;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void showInfo() {
		System.out.println("�����ײͣ�ͨ��ʱ��Ϊ"+this.getTalkTime()+"����/��,"
				+ "��������Ϊ"+this.getSmsCount()+"��/��,��������Ϊ"
				+this.getFlow()+"GB/��,�ʷ�"+this.getPrice()+"/�¡�");
	}
	
	@Override
    public int call(int minCount, MobileCard card) throws Exception{
        int temp = minCount;
        for(int i = 0; i < minCount; i++){
            if(this.talkTime - card.getRealTakTime() >= 1){
                card.setRealTakTime(card.getRealTakTime() + 1);
            }else if(card.getMoney() >= 0.2){
                card.setRealTakTime(card.getRealTakTime() + 1);
                card.setMoney(card.getMoney() - 0.2);
                card.setConsumAmout(card.getConsumAmout() + 0.2);
            }else{
                temp = i; 
                throw new Exception("�����Ѿ�ͨ��" + i + "���ӣ��������㣬���ֵ����ʹ�ã�");
            }
        }
        return temp;
    }
 
    @Override

    public int netPlay(int flow, MobileCard card) throws Exception {
        int temp = flow;
        for(int i = 0; i < flow; i++){
            if(this.flow - card.getRealFlow() >= 1){
                card.setRealFlow(card.getRealFlow() + 1);
            }else if(card.getMoney() >= 0.1){
                card.setRealFlow(card.getRealFlow() + 1); 
                card.setMoney(card.getMoney() - 0.1);
                card.setConsumAmout(card.getConsumAmout() + 0.1);
            }else{
                temp = i;
                throw new Exception("�����Ѿ�ʹ��" + i + "MB���������㣬���ֵ����ʹ�ã�");
            }
        }
        return temp;
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
                throw new Exception("�����Ѿ�����" + i + "�����������㣬���ֵ����ʹ�ã�");
            }
        }
        return temp;
    }
}