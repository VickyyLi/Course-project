package alg.ub.predictor;

import java.util.Map;

import alg.ub.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class SimpleNonPersonalisedPredictor implements Predictor{

	public Double getPrediction(Integer userId, Integer itemId, Map<Integer, Profile> userProfileMap,
			Map<Integer, Profile> itemProfileMap, Neighbourhood neighbourhood, SimilarityMap simMap) {
		float answer_sum=0;
		Integer count=0;
		for(Integer i:userProfileMap.get(userId).getIds()) {
			if(neighbourhood.isNeighbour(itemId,i)) {
				answer_sum = answer_sum + userProfileMap.get(userId).getValue(i).floatValue();
				count+=1;
			}
		}
		return (double) ((count==0) ? 0:answer_sum/count);
	}

}
