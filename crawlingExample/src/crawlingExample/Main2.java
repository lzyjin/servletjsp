package crawlingExample;

import java.io.*;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Main2 {

	public static void main(String[] args) {
		
		String url = "https://www.melon.com/chart/index.htm";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements elements = doc.select("tbody");
		
		Iterator<Element> div1 = elements.select("div.ellipsis.rank01").iterator();
		Iterator<Element> div2 = elements.select("div.ellipsis.rank02").iterator();
		
		System.out.println("==================================================");
		System.out.println("\t현재 멜론차트 100");
		System.out.println("==================================================");

		int i = 1;
		
		while(div1.hasNext()) {
			System.out.print(i + "위\t");			
			System.out.println(div1.next().select("a").text() + "\t" + div2.next().children().first().text());
			
			i++;
		}
		
		System.out.println("======================================================");
		
//		for(Element el : elements.select("div.ellipsis.rank01")) {    
//			
//            System.out.println(el.select("a").text() +"\t" +  el.nextElementSibling().nextElementSibling().select("a").text());
//      
//		}

	
	}
}


