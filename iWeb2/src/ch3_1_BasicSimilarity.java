import org.yooreeka.algos.reco.collab.data.MusicData;
import org.yooreeka.algos.reco.collab.data.MusicUser;


public class ch3_1_BasicSimilarity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ------------------------------------------------------
//	    The notion of similarity for recommendations
	// ------------------------------------------------------


	//
	//  Load some users with predetermined ratings
	//
	MusicUser[] mu = MusicData.loadExample();

	//
	// Compare the first user with the second user (high similarity)
	//
	mu[0].getSimilarity(mu[1],0);

	//
	// Correction for the ratio of common items
	//
	mu[0].getSimilarity(mu[1],1);

	//
	// Compare the first user with the third user (low similarity)
	//
	mu[0].getSimilarity(mu[2],1);


	//
	// Show symmetry property
	//
	mu[1].getSimilarity(mu[2],0);

	mu[2].getSimilarity(mu[1],0);


	}

}
