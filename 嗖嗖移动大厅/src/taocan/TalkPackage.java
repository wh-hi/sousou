package taocan;
 
import 嗖嗖移动大厅.Common;
import jiekou.CallService;
import jiekou.SendService;
import 嗖嗖移动大厅.MobileCard;
 

public class TalkPackage extends ServicePackage implements CallService,SendService {
	//通话时长
	private int talkTime;
	//短信条数
	private int smsCount;
	//套餐月资费
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
		System.out.println("话唠套餐：通话时长为：" + this.getTalkTime() + "分钟/月,短信条数为：" 
				+ this.getSmsCount() + "条/月,资费为："
				+ this.getPrice() + "元/月" );
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
                 throw new Exception("短信已经发送" + i + "条，您的余额不足，请充值后再使用！");             }
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
				throw new Exception("本次通话"+i+"分钟，您的余额不足，请充值后再使用！");
			}
		}
		return temp;
	}
}