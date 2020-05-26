package alg.ub.predictor;

import java.util.Map;
import java.util.Set;

import alg.ub.neighbourhood.Neighbourhood;
import profile.Profile;
import similarity.SimilarityMap;

public class DeviationFromUserMeanPredictor implements Predictor{

	public Double getPrediction(Integer userId, Integer itemId, Map<Integer, Profile> userProfileMap,
			Map<Integer, Profile> itemProfileMap, Neighbourhood neighbourhood, SimilarityMap simMap) {
		double mean_rating = userProfileMap.get(userId).getMeanValue();
		
		double sum_above=0,sum_below=0,sum_part=0;
		
		Set<Integer> neighbours = neighbourhood.getNeighbours(userId);

		// return null if the user has no neighbours
		if (neighbours == null)
			return null;
		
		for(Integer i : neighbours) {
				Set<Integer> all_item = userProfileMap.get(i).getIds();
				if (all_item.contains(itemId)) {
					sum_part = userProfileMap.get(i).getValue(itemId).doubleValue();
					sum_part -= userProfileMap.get(i).getMeanValue();
					sum_above += simMap.getSimilarity(userId, i)*sum_part;
					sum_below += Math.abs(simMap.getSimilarity(userId, i));
				}
		}
		
		return (sum_below>0)?(mean_rating+(sum_above/sum_below)):null;
	}

}
