package alg.ub.neighbourhood;

import profile.Profile;
import similarity.SimilarityMap;

public class ThresholdNeighbourhood extends Neighbourhood{
	private double neighbourthre;

	public ThresholdNeighbourhood(double neighbourthre ) {
		super();
		this.neighbourthre = neighbourthre;
	}

	public void computeNeighbourhoods(SimilarityMap simMap) {
		for (Integer i: simMap.getIds()){
			Profile profile = simMap.getSimilarities(i);
			if(profile != null){
				for(Integer j: profile.getIds()){
					double neighbour_dis = profile.getValue(j);
					if(neighbour_dis > neighbourthre)
						this.add(i, j);
				}
			}
		}	
	}
}
