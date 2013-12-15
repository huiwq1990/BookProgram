

import org.yooreeka.config.YooreekaConfigurator;
import org.yooreeka.examples.search.LuceneIndexer;
import org.yooreeka.examples.search.MySearcher;
import org.yooreeka.util.internet.crawling.FetchAndProcessCrawler;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ------------------------------------------------------
	//   Collecting data and searching with Lucene
	// ------------------------------------------------------

	

	//
	// -- Data (default URL list)
	//
	String yHome = YooreekaConfigurator.getHome();
	FetchAndProcessCrawler crawler = new FetchAndProcessCrawler(yHome+"/data/ch02",5,200);
	crawler.setDefaultUrls(); 
	crawler.run(); 

	//
	// -- Lucene
	//
	LuceneIndexer luceneIndexer = new LuceneIndexer(crawler.getRootDir());
	luceneIndexer.run(); 

	MySearcher oracle = new MySearcher(luceneIndexer.getLuceneDir());

	oracle.search("armstrong",5); 


	}

}
