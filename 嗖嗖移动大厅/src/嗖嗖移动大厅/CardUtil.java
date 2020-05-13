package ���ƶ�����;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
 
import ���ƶ�����.Common;
import ���ƶ�����.ConsumInfo;
import ���ƶ�����.MobileCard;
import taocan.NetPackage;
import ���ƶ�����.Scene;
import taocan.ServicePackage;
import taocan.SuperPackage;
import taocan.TalkPackage;
import jiekou.CallService;
import jiekou.SendService;
 

public class CardUtil {
	Scanner input=new Scanner(System.in);
	//�û��б�
	public static Map<String, MobileCard> cards = new HashMap<String, MobileCard>();
	//���п��ŵ����Ѽ�¼�б�
	public static Map<String, List<ConsumInfo>> consumInfos = new HashMap<String, List<ConsumInfo>>();
	//�����б�
	Map<Integer, Scene> scenes = new HashMap<Integer, Scene>();
	
	Scene scene1 = new Scene("ͨ��", 60, "��Ů��������60������");
	Scene scene2 = new Scene("����", 50, "����绰������δ�����ַ���50������");
	Scene scene3 = new Scene("����", 1024, "����Ϸ����1G����");

	public void intitScene() {
		MobileCard wh=new MobileCard("13870283177", "����", "123", new TalkPackage(), 78, 100, 300, 20, 100); 
		MobileCard ww=new MobileCard("13712345678", "����", "1234", new NetPackage(),88, 100, 50, 60, 500); 
		MobileCard hh=new MobileCard("13812345678", "����", "12345", new SuperPackage(), 98, 100, 100, 10, 4000); 
		
		CardUtil.cards.put("13870283177", wh); 
		CardUtil.cards.put("13712345678", ww); 
		CardUtil.cards.put("13812345678", hh); 
		
		ConsumInfo info=new ConsumInfo("13870283177", "ͨ��", 300);
		List<ConsumInfo> list=new ArrayList<ConsumInfo>();
		list.add(info);
		consumInfos.put(info.getNumber(), list);
		
	}
	
	public void addCard(MobileCard card) {
		cards.put(card.getCardNumber(), card);
	}
 
	public void chargeMoney(String number, double money) {
		cards.get(number).setMoney(cards.get(number).getMoney()+money);
	}
 
	public void userSoso(String number) {
		
        scenes.put(1, scene1);
        scenes.put(2, scene2);
        scenes.put(3, scene3);

		MobileCard card=cards.get(number);
		ServicePackage pack=card.getSerPackage();
		Random random=new Random();
		int temp=0;	
		do {
			int ranNum=random.nextInt(3)+1;
			Scene scene=scenes.get(ranNum);
			switch (ranNum) {
			case 1:
				if (pack instanceof CallService) {
					System.out.println(scene.getDescription());
					CallService callService=(CallService)pack;
					try {
						temp=callService.call(scene.getData(), card);
					} catch (Exception e) {
						e.printStackTrace();
					}
					addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
					break;
				}else {
					continue;
				}
			case 2:
				if (pack instanceof SendService) {
					System.out.println(scene.getDescription());
					SendService sendService=(SendService)pack;
					try {
						temp=sendService.send(scene.getData(), card);
					} catch (Exception e) {
						e.printStackTrace();
					}
					addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
					break;
				}else {
					continue;
				}
			case 3:
				if (pack instanceof NetPackage) {
					System.out.println(scene.getDescription());
					NetPackage netPackage=(NetPackage)pack;
					try {
						temp=netPackage.netPlay(scene.getData(), card);
					} catch (Exception e) {
						e.printStackTrace();
					}
					addConsumInfo(number, new ConsumInfo(number, scene.getType(), temp));
					break;
				}else {
					continue;
				}
			}
			break;
		} while (true);
		
	}
	public void showDescription() {
		 	File file = new File("D:\\MyEclipse\\Myeclipse_2017\\�½��ļ���\\���ƶ�����\\�ײ��ʷ�˵��.txt");
	        BufferedReader br = null;
	        try {
	            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
	            String line = null;
	            while((line = br.readLine()) != null){
	                System.out.println(line);
	            }
	        } catch (FileNotFoundException e) {  
	            System.out.println("file is not fond");  
	        } catch (IOException e) {  
	            System.out.println("Read Exceptioned");  
	        }finally{
	            try {
	                if(br != null){
	                    br.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
 
	
	public void showAmountDetail(String number) {
		StringBuffer meg = new StringBuffer();
		MobileCard card=cards.get(number);
		meg.append("���Ŀ���:"+card.getCardNumber()+"\n�����˵�Ϊ:\n");
		meg.append("�ײ��ʷ�:"+card.getSerPackage().getPrice()+"Ԫ\n");
		meg.append("�ϼ�:"+Common.dataFormat(card.getConsumAmout())+"Ԫ\n");
		meg.append("�˺����:"+Common.dataFormat(card.getMoney())+"Ԫ");
		System.out.println(meg);
	}
 
	public void showRemainDetail(String number) {
		MobileCard card=cards.get(number);
		int remainTalkTime;
		int remainSmsCount;
		double remainFlow;
		StringBuffer meg=new StringBuffer();
		meg.append("���Ŀ�����"+number+"\n�ײ���ʣ��:\n");
		ServicePackage pack=card.getSerPackage();
		if (pack instanceof TalkPackage) {
			TalkPackage cardPack=(TalkPackage)pack;
			if(cardPack.getTalkTime()>card.getRealTakTime()){
			remainTalkTime=cardPack.getTalkTime()-card.getRealTakTime();
			meg.append("ͨ��ʱ��:"+remainTalkTime+"����\n");
			}else{
				meg.append("ͨ��ʱ��:"+0+"����\n");
			}
			if(cardPack.getSmsCount()>card.getRealSMSCount()){
			remainSmsCount=cardPack.getSmsCount()-card.getRealSMSCount();
			meg.append("��������:"+remainSmsCount+"��");
			}else{
				meg.append("��������:"+0+"��");
			}
		}else if (pack instanceof NetPackage) {
			NetPackage netPackage=(NetPackage)pack;
			if(netPackage.getFlow()*1024>card.getRealFlow()){
			remainFlow=netPackage.getFlow()*1024-card.getRealFlow();
			meg.append("��������:"+(remainFlow/1024)+"GB");
			}else{
				meg.append("��������:"+0+"GB");
			}
		}else {
			SuperPackage superPackage=(SuperPackage)pack;
			if(superPackage.getTalkTime()>card.getRealTakTime()){
				remainTalkTime=superPackage.getTalkTime()-card.getRealTakTime();
				meg.append("ͨ��ʱ��:"+remainTalkTime+"����\n");
				}else{
					meg.append("ͨ��ʱ��:"+0+"����\n");
				}
			if(superPackage.getSmsCount()>card.getRealSMSCount()){
				remainSmsCount=superPackage.getSmsCount()-card.getRealSMSCount();
				meg.append("��������:"+remainSmsCount+"��");
				}else{
					meg.append("��������:"+0+"��");
				}
			if(superPackage.getFlow()*1024>card.getRealFlow()){
			remainFlow=superPackage.getFlow()*1024-card.getRealFlow();
			meg.append("\n��������:"+(remainFlow/1024)+"GB");
			}else{
				meg.append("\n��������:"+0+"GB");
			}
		} 
		System.out.println(meg);
	}
 
    public void printAmountDetail(String number){   
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\MyEclipse\\Myeclipse_2017\\�½��ļ���\\���ƶ�����\\consumes.txt");
            Set<String> numbers = consumInfos.keySet();
            Iterator<String> it = numbers.iterator();
            List<ConsumInfo> infos = new ArrayList<ConsumInfo>();
            infos = consumInfos.get(number);
            boolean isExist = false;
            while(it.hasNext()){
                String numberKey = it.next();
                if(number.equals(numberKey)){
                    isExist = true;
                }
            }           
            if(isExist){
                StringBuffer content = new StringBuffer("*********" + number + "���Ѽ�¼*********\n");
                content.append("���\t����\t���ݣ�ͨ�������ӣ�/������MB��/���ţ�������\n");
                for(int i = 0; i < infos.size(); i++){
                    ConsumInfo info = infos.get(i);
                    content.append((i + 1) + ".\t" + info.getType() + "\t" + info.getConsumData() + "\n");
                }
                fileWriter.write(content.toString());
                fileWriter.flush();
                System.out.println("�����嵥��ӡ�ɹ��������ļ��в鿴��");
            }else{
                System.out.println("�����ڴ˺�������Ѽ�¼����ӡʧ�ܣ�");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
	public void chargeingPack(String number) {
		System.out.println("1.�����ײ� 2.�����ײ� 3.�����ײ� ����ѡ��(���)��");
        int packNum = input.nextInt();
        switch(packNum){
        case 1: 
            if(cards.get(number).getSerPackage() instanceof TalkPackage){
                System.out.println("���Ѿ��Ǹ��ײ��û�����������ײͣ�");
            }else{
                if(cards.get(number).getMoney() < 58){
                    System.out.println("�Բ���,���ĵ�������֧���µ��ײͱ����ʷѣ����ֵ���ٰ���ҵ��");
                }else{
                    cards.get(number).setSerPackage(createPackage(packNum));
                    System.out.println("�ײ͸����ɹ���" );
                    cards.get(number).setMoney(cards.get(number).getMoney()-58);
                    createPackage(packNum).showInfo();
                }
            }
            break;
        case 2: 
            if(cards.get(number).getSerPackage() instanceof NetPackage){
                System.out.println("���Ѿ��Ǹ��ײ��û�����������ײͣ�");
            }else{
                if(cards.get(number).getMoney() <68){
                    System.out.println("�Բ���,���ĵ�������֧���µ��ײͱ����ʷѣ����ֵ���ٰ���ҵ��");
                }else{
                	 cards.get(number).setRealSMSCount(0);
                     cards.get(number).setRealTakTime(0);
                     cards.get(number).setRealFlow(0);
                    cards.get(number).setSerPackage(createPackage(packNum));
                    System.out.println("�ײ͸����ɹ���" );
                    cards.get(number).setMoney(cards.get(number).getMoney()-68);
                    createPackage(packNum).showInfo();
                }
            }
            break;
        case 3:
            if(cards.get(number).getSerPackage() instanceof SuperPackage){
                System.out.println("���Ѿ��Ǹ��ײ��û�����������ײͣ�");
            }else{
                if(cards.get(number).getMoney() < 78){
                    System.out.println("�Բ���,���ĵ�������֧���µ��ײͱ����ʷѣ����ֵ���ٰ���ҵ��");
                }else{
                    cards.get(number).setRealSMSCount(0);
                    cards.get(number).setRealTakTime(0);
                    cards.get(number).setRealFlow(0);
                    cards.get(number).setSerPackage(createPackage(packNum));
                    System.out.println("�ײ͸����ɹ���" );
                    cards.get(number).setMoney(cards.get(number).getMoney()-78);
                    createPackage(packNum).showInfo();
                }
            }
            break;
        }
	}
 
	public void delCard(String number) {
		if (cards.containsKey(number)) {
			cards.remove(number); 
			System.out.println("����:"+number+"���������ɹ���");
			System.out.println("ллʹ�ã�");
		}else {
			System.out.println("�ֻ�������������");
		}
	}

	public boolean isExistCard(String number, String pwd) {
		if (cards.containsKey(number)&&(cards.get(number).getPassWord().equals(pwd))) {
				return true;
		}else{
		System.out.println("��¼ʧ��!");
		return false;
		}
	}
 
	public boolean isExistCard(String number) {
		Set<String>	numbers=cards.keySet();
		Iterator<String> iterator=numbers.iterator();
		while (iterator.hasNext()) {
			String str=iterator.next();
			if (str.equals(number))
				return true;
		}
		return false;
	}
 
    public String createNumber(){
        Random random = new Random();
        boolean isExist = false;
        String number = "";
        int temp = 0;
        do{
            isExist = false;
            do{
                temp = random.nextInt(100000000);
            }while(temp < 10000000);
            number = "139" + temp;
            if(cards != null){ 
                Set<String> cardNumbers = cards.keySet();
                for(String cardNumber : cardNumbers){
                    if(number.equals(cardNumber)){
                        isExist = true;
                        break;
                    }
                }
            }
        }while(isExist);
        return number;
    }
    
	public String[] getNewNumber(int count) {
		 String[] strs = new String[count];
	        for (int i = 0; i < count; i++) {
	            strs[i] = createNumber();
	        }
	        return strs ;
	}
 
	public void addConsumInfo(String number, ConsumInfo info) {
		if (consumInfos.containsKey(number)) {
			consumInfos.get(number).add(info);
        }else{
            List<ConsumInfo> list = new ArrayList<ConsumInfo>();
            list.add(info);
            consumInfos.put(number, list);
        }
	}
	public ServicePackage createPackage(int packId) {
		ServicePackage servicePackage=null;
		switch (packId) {
		case 1:
			servicePackage=new TalkPackage();
			break;
		case 2:
			servicePackage=new NetPackage();
			break;
		case 3:
			servicePackage=new SuperPackage();
			break;
		default:
			break;
		}
		return servicePackage;
	}
}