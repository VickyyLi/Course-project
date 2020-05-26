package similarity.metric;

import java.util.Set;

import profile.Profile;

public class MeanSquaredDifferenceMetric implements SimilarityMetric{
	
	private float min_num;
	private float max_num;

	public MeanSquaredDifferenceMetric(int i, int j) {
		// TODO Auto-generated constructor stub
		float min_num = i;
		float max_num = j;
	}

	public double getSimilarity(Profile p1, Profile p2) {
		Set<Integer> common_set= p1.getCommonIds(p2);
		Integer count_common = common_set.size();
		float answer=0;
		for(Integer j:common_set) {
			answer = (float) (answer + (p1.getValue(j)-p2.getValue(j))*(p1.getValue(j)-p2.getValue(j)));
		}
		float MSD = (count_common==0)?0:answer/count_common;
		
		return 1-(MSD)/((max_num-min_num)*(max_num-min_num));
	}
}
