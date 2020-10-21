package shoestore.service;

import java.util.List;

import shoestore.dto.PointDTO;

public interface IPointService {
	public void addNewPoint(PointDTO pointDTO);
	public List<PointDTO> getPointByUrl(PointDTO pointDTO);
	public Integer getMaxValueInScreen(PointDTO pointDTO);
	public Integer getMinValueInScreen(PointDTO pointDTO);
}
