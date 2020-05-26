package alg.ub.predictor;

import java.util.Map;

import alg.ub.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class DeviationFromUserMeanPredictor implements Predictor{

	public Double getPrediction(Integer userId, Integer itemId, Map<Integer, Profile> userProfileMap,
			Map<Integer, Profile> itemProfileMap, Neighbourhood neighbourhood, SimilarityMap simMap) {
		double mean_rating = itemProfileMap.get(itemId).getMeanValue();
		
		double sum_above=0,sum_below=0;
		for(Integer i : userProfileMap.get(userId).getIds()) {
			
			if(neighbourhood.isNeighbour(itemId, i)) {
				
				double sum_part =  userProfileMap.get(userId).getValue(i).doubleValue() -itemProfileMap.get(i).getMeanValue();
				sum_above += simMap.getSimilarity(itemId, i)*sum_part;
				sum_below += simMap.getSimilarity(itemId, i);
			}
		}
		double answer = (sum_below==0)?0:sum_above/sum_below;
		return (mean_rating+answer);
	}

}
