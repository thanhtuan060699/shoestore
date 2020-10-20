package shoestore.api.heatmap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import shoestore.dto.PointDTO;

@RestController
public class HeatmapClickAPI {
	@RequestMapping(value = "/api/heatmap/click/add",method = RequestMethod.POST)
	public void getClickValue(@RequestBody PointDTO pointDTO,HttpServletRequest request) {
		String url=pointDTO.getUrl().replace("http://localhost:8080/", "");
		System.out.println(url);
	}
}
