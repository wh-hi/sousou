package taocan;
 
import jiekou.NetService;
import 嗖嗖移动大厅.MobileCard;
 
public class NetPackage extends ServicePackage implements NetService {
	
	private int flow;
	
	private double price;
	
	public NetPackage() {
		this.flow =3;
		this.price = 68;
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
		System.out.println("网虫套餐：上网流量为：" + this.getFlow()
				+ "GB/月," + "资费为：" + this.getPrice() + "元/月");
	}
	
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
}