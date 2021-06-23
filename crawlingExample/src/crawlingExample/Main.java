package crawlingExample;

import java.io.*;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Main {

	public static void main(String[] args) {
		
		// Document 클래스 : 연결해서 얻어온 HTML 전체 문서 
		// Element 클래스 : Documnet의 HTML 요소 
		// Elements 클래스 : Element가 모인 자료형

		
		String url = "http://www.cgv.co.kr/movies/";
		Document doc = null;
		
		// url : 크롤링할 사이트의 url주소 
		// Document에 사이트의 전체 소스 저장 
		
		try {
			
			doc = Jsoup.connect(url).get();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		// select() : 원하는 태그 선택 
		Elements element = doc.select("div.sect-movie-chart");
		
				System.out.println("==============================");
		
		Iterator<Element> ie1 = element.select("strong.rank").iterator();
		Iterator<Element> ie2 = element.select("strong.title").iterator();
		
		
		while(ie1.hasNext()) {
			System.out.println(ie1.next().text() + "\t" + ie2.next().text());
		}
		
				System.out.println("==============================");
		
	}

}
