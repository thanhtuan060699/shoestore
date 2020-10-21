package shoestore.repository.custom;

public interface PointRepositoryCustom {
	public int getMaxValueInScreen(Long screenId,String type);
	public int getMinValueInScreen(Long screenId,String type);
}
