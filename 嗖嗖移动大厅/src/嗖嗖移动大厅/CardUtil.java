package 嗖嗖移动大厅;
 
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
 
import 嗖嗖移动大厅.Common;
import 嗖嗖移动大厅.ConsumInfo;
import 嗖嗖移动大厅.MobileCard;
import taocan.NetPackage;
import 嗖嗖移动大厅.Scene;
import taocan.ServicePackage;
import taocan.SuperPackage;
import taocan.TalkPackage;
import jiekou.CallService;
import jiekou.SendService;
 

public class CardUtil {
	Scanner input=new Scanner(System.in);
	//用户列表
	public static Map<String, MobileCard> cards = new HashMap<String, MobileCard>();
	//所有卡号的消费记录列表
	public static Map<String, List<ConsumInfo>> consumInfos = new HashMap<String, List<ConsumInfo>>();
	//场景列表
	Map<Integer, Scene> scenes = new HashMap<Integer, Scene>();
	
	Scene scene1 = new Scene("通话", 60, "跟女朋友煲了60分钟粥");
	Scene scene2 = new Scene("短信", 50, "打完电话后意犹未尽，又发了50条短信");
	Scene scene3 = new Scene("上网", 1024, "打游戏用了1G流量");

	public void intitScene() {
		MobileCard wh=new MobileCard("13870283177", "张三", "123", new TalkPackage(), 78, 100, 300, 20, 100); 
		MobileCard ww=new MobileCard("13712345678", "李四", "1234", new NetPackage(),88, 100, 50, 60, 500); 
		MobileCard hh=new MobileCard("13812345678", "王五", "12345", new SuperPackage(), 98, 100, 100, 10, 4000); 
		
		CardUtil.cards.put("13870283177", wh); 
		CardUtil.cards.put("13712345678", ww); 
		CardUtil.cards.put("13812345678", hh); 
		
		ConsumInfo info=new ConsumInfo("13870283177", "通话", 300);
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
		 	File file = new File("D:\\MyEclipse\\Myeclipse_2017\\新建文件夹\\嗖嗖移动大厅\\套餐资费说明.txt");
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
		meg.append("您的卡号:"+card.getCardNumber()+"\n当月账单为:\n");
		meg.append("套餐资费:"+card.getSerPackage().getPrice()+"元\n");
		meg.append("合计:"+Common.dataFormat(card.getConsumAmout())+"元\n");
		meg.append("账号余额:"+Common.dataFormat(card.getMoney())+"元");
		System.out.println(meg);
	}
 
	public void showRemainDetail(String number) {
		MobileCard card=cards.get(number);
		int remainTalkTime;
		int remainSmsCount;
		double remainFlow;
		StringBuffer meg=new StringBuffer();
		meg.append("您的卡号是"+number+"\n套餐内剩余:\n");
		ServicePackage pack=card.getSerPackage();
		if (pack instanceof TalkPackage) {
			TalkPackage cardPack=(TalkPackage)pack;
			if(cardPack.getTalkTime()>card.getRealTakTime()){
			remainTalkTime=cardPack.getTalkTime()-card.getRealTakTime();
			meg.append("通话时长:"+remainTalkTime+"分钟\n");
			}else{
				meg.append("通话时长:"+0+"分钟\n");
			}
			if(cardPack.getSmsCount()>card.getRealSMSCount()){
			remainSmsCount=cardPack.getSmsCount()-card.getRealSMSCount();
			meg.append("短信条数:"+remainSmsCount+"条");
			}else{
				meg.append("短信条数:"+0+"条");
			}
		}else if (pack instanceof NetPackage) {
			NetPackage netPackage=(NetPackage)pack;
			if(netPackage.getFlow()*1024>card.getRealFlow()){
			remainFlow=netPackage.getFlow()*1024-card.getRealFlow();
			meg.append("上网流量:"+(remainFlow/1024)+"GB");
			}else{
				meg.append("上网流量:"+0+"GB");
			}
		}else {
			SuperPackage superPackage=(SuperPackage)pack;
			if(superPackage.getTalkTime()>card.getRealTakTime()){
				remainTalkTime=superPackage.getTalkTime()-card.getRealTakTime();
				meg.append("通话时长:"+remainTalkTime+"分钟\n");
				}else{
					meg.append("通话时长:"+0+"分钟\n");
				}
			if(superPackage.getSmsCount()>card.getRealSMSCount()){
				remainSmsCount=superPackage.getSmsCount()-card.getRealSMSCount();
				meg.append("短信条数:"+remainSmsCount+"条");
				}else{
					meg.append("短信条数:"+0+"条");
				}
			if(superPackage.getFlow()*1024>card.getRealFlow()){
			remainFlow=superPackage.getFlow()*1024-card.getRealFlow();
			meg.append("\n上网流量:"+(remainFlow/1024)+"GB");
			}else{
				meg.append("\n上网流量:"+0+"GB");
			}
		} 
		System.out.println(meg);
	}
 
    public void printAmountDetail(String number){   
        Writer fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\MyEclipse\\Myeclipse_2017\\新建文件夹\\嗖嗖移动大厅\\consumes.txt");
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
                StringBuffer content = new StringBuffer("*********" + number + "消费记录*********\n");
                content.append("序号\t类型\t数据（通话（分钟）/上网（MB）/短信（条））\n");
                for(int i = 0; i < infos.size(); i++){
                    ConsumInfo info = infos.get(i);
                    content.append((i + 1) + ".\t" + info.getType() + "\t" + info.getConsumData() + "\n");
                }
                fileWriter.write(content.toString());
                fileWriter.flush();
                System.out.println("消费清单打印成功，请在文件中查看！");
            }else{
                System.out.println("不存在此号码的消费记录，打印失败！");
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
		System.out.println("1.话唠套餐 2.网虫套餐 3.超人套餐 ：请选择(序号)：");
        int packNum = input.nextInt();
        switch(packNum){
        case 1: 
            if(cards.get(number).getSerPackage() instanceof TalkPackage){
                System.out.println("您已经是该套餐用户，无需更换套餐！");
            }else{
                if(cards.get(number).getMoney() < 58){
                    System.out.println("对不起,您的的余额不足以支付新的套餐本月资费，请充值后再办理业务！");
                }else{
                    cards.get(number).setSerPackage(createPackage(packNum));
                    System.out.println("套餐更换成功！" );
                    cards.get(number).setMoney(cards.get(number).getMoney()-58);
                    createPackage(packNum).showInfo();
                }
            }
            break;
        case 2: 
            if(cards.get(number).getSerPackage() instanceof NetPackage){
                System.out.println("您已经是该套餐用户，无需更换套餐！");
            }else{
                if(cards.get(number).getMoney() <68){
                    System.out.println("对不起,您的的余额不足以支付新的套餐本月资费，请充值后再办理业务！");
                }else{
                	 cards.get(number).setRealSMSCount(0);
                     cards.get(number).setRealTakTime(0);
                     cards.get(number).setRealFlow(0);
                    cards.get(number).setSerPackage(createPackage(packNum));
                    System.out.println("套餐更换成功！" );
                    cards.get(number).setMoney(cards.get(number).getMoney()-68);
                    createPackage(packNum).showInfo();
                }
            }
            break;
        case 3:
            if(cards.get(number).getSerPackage() instanceof SuperPackage){
                System.out.println("您已经是该套餐用户，无需更换套餐！");
            }else{
                if(cards.get(number).getMoney() < 78){
                    System.out.println("对不起,您的的余额不足以支付新的套餐本月资费，请充值后再办理业务！");
                }else{
                    cards.get(number).setRealSMSCount(0);
                    cards.get(number).setRealTakTime(0);
                    cards.get(number).setRealFlow(0);
                    cards.get(number).setSerPackage(createPackage(packNum));
                    System.out.println("套餐更换成功！" );
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
			System.out.println("卡号:"+number+"办理退网成功！");
			System.out.println("谢谢使用！");
		}else {
			System.out.println("手机号码输入有误！");
		}
	}

	public boolean isExistCard(String number, String pwd) {
		if (cards.containsKey(number)&&(cards.get(number).getPassWord().equals(pwd))) {
				return true;
		}else{
		System.out.println("登录失败!");
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