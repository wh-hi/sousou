package taocan;
 
import jiekou.NetService;
import ���ƶ�����.MobileCard;
 
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
		System.out.println("�����ײͣ���������Ϊ��" + this.getFlow()
				+ "GB/��," + "�ʷ�Ϊ��" + this.getPrice() + "Ԫ/��");
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
                throw new Exception("�����Ѿ�ʹ��" + i + "MB���������㣬���ֵ����ʹ�ã�");
            }
        }
        return temp;
	}
}