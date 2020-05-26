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
		this.sigWeightingVal = sigWeight;
	}

	public double getSimilarity(Profile p1, Profile p2) {
		// TODO Auto-generated method stub
		Set<Integer> common_set = p1.getCommonIds(p2);
		float answer_sum =0,answer_aj=0,answer_ij=0;
		for(Integer j :common_set) {
			answer_sum = (float) (answer_sum + (p1.getValue(j)-p1.getMeanValue())*(p2.getValue(j)-p2.getMeanValue()));
			answer_aj = (float) (answer_aj+(p1.getValue(j)-p1.getMeanValue())*(p1.getValue(j)-p1.getMeanValue()));
			answer_ij = (float)(answer_ij+(p2.getValue(j)-p2.getMeanValue())*(p2.getValue(j)-p2.getMeanValue()));					
		}
		float answer = (float) ((Math.sqrt(answer_aj*answer_ij)==0)?0:answer_sum/(Math.sqrt(answer_aj*answer_ij)));
		return (common_set.size()<sigWeightingVal)?answer*(common_set.size()/sigWeightingVal):answer;
	}
}
