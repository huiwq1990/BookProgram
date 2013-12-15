import org.yooreeka.algos.reco.collab.data.BaseDataset;
import org.yooreeka.algos.reco.collab.data.MusicData;
import org.yooreeka.algos.reco.collab.data.MusicUser;
import org.yooreeka.algos.reco.collab.model.RecommendationType;
import org.yooreeka.algos.reco.collab.recommender.Delphi;



public class ch3_2_UserBasedSimilarity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ------------------------------------------------------
//	    Recommendations based on user similarity 
	// ------------------------------------------------------


	// The static method createDataset will create random ratings
	// for all the users, pick 75% of all the songs and assign ratings.
	//
	// Users whose name starts from A to D should have ratings between 3 and 5
	// Users whose name starts from E to Z should have ratings between 1 and 3
	//
	BaseDataset ds = MusicData.createDataset();

	//
	// Serialize the dataset, so that we can load it in the next script
	//

	ds.save("C:/iWeb2/data/ch3_2_dataset.ser");

	//
	// use Delphi with USER_BASED similarity
	//
	Delphi delphi = new Delphi(ds,RecommendationType.USER_BASED);
	delphi.setVerbose(true);

	//
	// Show me users like X (top 5)
	//
	MusicUser mu1 = (MusicUser) ds.pickUser("Bob");
	delphi.findSimilarUsers(mu1);

	MusicUser mu2 = (MusicUser) ds.pickUser("John");
	delphi.findSimilarUsers(mu2);

	//
	// Show me recommendations for user X (top 5)
	//
	delphi.recommend(mu1);

	//------------------------------------------------------------------------
	//BaseDataset ds = MusicData.createDataset();
	//ds.save("C:/iWeb2/deploy/data/ch3_2_dataset.ser");
	//Delphi delphi = new Delphi(ds,RecommendationType.USER_BASED);
	//delphi.setVerbose(true);
	//MusicUser mu1 = ds.pickUser("Bob");
	//delphi.findSimilarUsers(mu1);
	//MusicUser mu2 = ds.pickUser("John");
	//delphi.findSimilarUsers(mu2);
	//delphi.recommend(mu1);
	//------------------------------------------------------------------------

	}

}
