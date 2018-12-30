package bitcoin;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Coin {
	
	public String[] coinName() {
		String[] name = null; 
		int count = 0;
		
		try {
			Document doc = Jsoup.connect("https://www.coinmarketkorea.com/m/coinrank/").get();
			Elements coinName = doc.select(".cname.t2 > a[href]");
			
			String nameList = coinName.html();
			
			nameList = nameList.replaceAll("<br>", " ");
			
			name = nameList.split("\\n");
			
		} catch (IOException e) { //Jsoup의 connect 부분에서 IOException 오류가 날 수 있으므로 사용한다.   
			e.printStackTrace();
		}
		
		return name;
	}
	
	//코인의 가격을 배열로 가져오는 함수
	public int[] coinPrice() {
		String[] str; 
		int[] price = new int[50];
		String priceCut;
		
		int count = 0;
		int idx;
		
		try {
			Document doc = Jsoup.connect("https://www.coinmarketkorea.com/m/coinrank/").get();
			Elements coinPrice = doc.select(".cr.t3");
			String pricelist = coinPrice.text();
			pricelist = pricelist.replaceAll(",", "");
			
			str = pricelist.split(" ");
			
			for (int i=0; i<str.length; i++) {
				if(i%2 != 0) {
					str[i] = str[i].substring(1, str[i].length());
					idx = str[i].indexOf(".");
					priceCut = str[i].substring(0, idx);
					price[count] = Integer.parseInt(priceCut);
					count++;
				}
			}
		
		} catch (IOException e) { //Jsoup의 connect 부분에서 IOException 오류가 날 수 있으므로 사용한다.   
			e.printStackTrace();
		}
		
		return price;
		}
}