package similarity.metric;

import java.util.Set;

import profile.Profile;

public class PearsonSigWeightingMetric implements SimilarityMetric {
	
	private int sigWeightingVal;

	/**
	 * constructor - creates a new PearsonSigWeightingMetric object
	 */
	public PearsonSigWeightingMetric(int sigWeight)
	{
		sigWeightingVal = sigWeight;
	}

	public double getSimilarity(Profile p1, Profile p2) {
		// TODO Auto-generated method stub
		
		Set<Integer> common_set = p1.getCommonIds(p2);
		double size_ = common_set.size();
		
		double answer_sum =0,answer_aj=0,answer_ij=0,average_a=0,average_i=0;
		for(Integer j :common_set) {
			average_a+=p1.getValue(j).doubleValue();
			average_i+=p2.getValue(j).doubleValue();
		}
		average_a = (size_>0)?average_a/size_:0;
		average_i = (size_>0)?average_i/size_:0;
		
		
		
		for(Integer j :common_set) {
			answer_sum+=((p1.getValue(j).doubleValue()-average_a)*(p2.getValue(j).doubleValue()-average_i));
			answer_aj +=Math.pow((p1.getValue(j).doubleValue()-average_a), 2);
			answer_ij +=Math.pow((p2.getValue(j).doubleValue()-average_i),2);				
		}
		
		double below = Math.sqrt(answer_aj*answer_ij);
		double sim =  (below==0)?0: (answer_sum/below);
		return (common_set.size()<sigWeightingVal)?((double)sim*common_set.size()/sigWeightingVal):sim;
	}
}
