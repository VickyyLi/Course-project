package alg.ub.predictor;

import java.util.Map;
import java.util.Set;

import alg.ub.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class WeightedAveragePredictor implements Predictor{

	public Double getPrediction(Integer userId, Integer itemId, Map<Integer, Profile> userProfileMap,
			Map<Integer, Profile> itemProfileMap, Neighbourhood neighbourhood, SimilarityMap simMap) {

		double above=0;
		double below=0;
		Set<Integer> neighbours = neighbourhood.getNeighbours(userId);

		// return null if the user has no neighbours
		if (neighbours == null)
			return null;
		
		
		for(Integer i :neighbours) {
			Set<Integer> all_item = userProfileMap.get(i).getIds();
			if (all_item.contains(itemId)) {
				double answer_part = simMap.getSimilarity(i, userId) *userProfileMap.get(i).getValue(itemId).doubleValue();
				above +=answer_part;
				below += simMap.getSimilarity(i, userId);
			}
	}
		return (below==0) ? null:(above/below);
	
	
	}

}
