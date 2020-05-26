package similarity.metric;

import java.util.Set;

import profile.Profile;

public class MeanSquaredDifferenceMetric implements SimilarityMetric{
	
	private float min_num;
	private float max_num;

	public MeanSquaredDifferenceMetric(int i, int j) {
		// TODO Auto-generated constructor stub
		min_num = i;
		max_num = j;
	}

	public double getSimilarity(Profile p1, Profile p2) {
		
		Set<Integer> common_set= p1.getCommonIds(p2);
		
		Integer count_common = common_set.size();
		
		double answer=0;
		for(Integer j:common_set) {
			answer+= Math.pow((p1.getValue(j).doubleValue()-p2.getValue(j).doubleValue()), 2);
		}
		double square = Math.pow((max_num-min_num), 2);
		return (count_common>0)?(1-((answer/count_common)/square)):0;
	}
}
