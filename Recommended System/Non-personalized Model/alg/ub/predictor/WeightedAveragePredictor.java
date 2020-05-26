package alg.ub.predictor;

import java.util.Map;

import alg.ub.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class WeightedAveragePredictor implements Predictor{

	public Double getPrediction(Integer userId, Integer itemId, Map<Integer, Profile> userProfileMap,
			Map<Integer, Profile> itemProfileMap, Neighbourhood neighbourhood, SimilarityMap simMap) {

		double answer_sum=0;
		double answer_sim=0;
		for(Integer i:userProfileMap.get(userId).getIds()) {
			if(neighbourhood.isNeighbour(itemId,i)) {
				answer_sum = (answer_sum + (simMap.getSimilarity(i, itemId))*userProfileMap.get(userId).getValue(i).floatValue());
				answer_sim = answer_sim + simMap.getSimilarity(i, itemId);
			}
		}
		return (answer_sim==0) ? 0:answer_sum/answer_sim;
	}

}
