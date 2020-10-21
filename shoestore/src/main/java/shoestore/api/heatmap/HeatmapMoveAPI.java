package shoestore.api.heatmap;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import shoestore.constant.SystemConstant;
import shoestore.dto.PointDTO;
import shoestore.service.impl.PointService;

@RestController
public class HeatmapMoveAPI {
	@Autowired
	PointService pointService;
	
	@RequestMapping(value = "/api/heatmap/move/add",method = RequestMethod.POST)
	public void addMoveValue(@RequestBody PointDTO pointDTO,HttpServletRequest request) {
		String url=pointDTO.getUrl().replace("http://localhost:8080/", "");
		pointDTO.setUrl(url);
		pointDTO.setUrl("heatmap/home");
		pointDTO.setType(SystemConstant.TYPE_MOVE);
		pointService.addNewPoint(pointDTO);
	}
	
	@RequestMapping(value = "/api/heatmap/move/get",method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> getMoveValue(@RequestBody PointDTO pointDTO,HttpServletRequest request) {
		HashMap<String, Object> hashMap=new HashMap<String, Object>();
		String url=pointDTO.getUrl().replace("http://localhost:8080/", "");
		pointDTO.setUrl("heatmap/home");
		pointDTO.setType(SystemConstant.TYPE_MOVE);
		List<PointDTO> pointDTOs=pointService.getPointByUrl(pointDTO);
		Integer maxValue=pointService.getMaxValueInScreen(pointDTO);
		Integer minValue=pointService.getMinValueInScreen(pointDTO);
		hashMap.put("points", pointDTOs);
		hashMap.put("min", minValue);
		hashMap.put("max", maxValue);
		return hashMap;
	}
}
