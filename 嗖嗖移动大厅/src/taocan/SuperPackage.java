package taocan;
 
import jiekou.CallService;
import jiekou.NetService;
import jiekou.SendService;
import 嗖嗖移动大厅.MobileCard;
 

public class SuperPackage extends ServicePackage implements CallService,SendService,NetService{
	//通话时长
	private int talkTime;
	//短信条数
	private int smsCount;
	//上网流量
	private int flow;
	//套餐月资费
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
		System.out.println("超人套餐：通话时长为"+this.getTalkTime()+"分钟/月,"
				+ "短信条数为"+this.getSmsCount()+"条/月,上网流量为"
				+this.getFlow()+"GB/月,资费"+this.getPrice()+"/月。");
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
                throw new Exception("本次已经通话" + i + "分钟，您的余额不足，请充值后再使用！");
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
                throw new Exception("流量已经使用" + i + "MB，您的余额不足，请充值后再使用！");
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
                throw new Exception("短信已经发送" + i + "条，您的余额不足，请充值后再使用！");
            }
        }
        return temp;
    }
}