package shoestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shoestore.entity.PointEntity;
import shoestore.entity.ScreenEntity;
import shoestore.repository.custom.PointRepositoryCustom;

public interface PointRepository extends JpaRepository<PointEntity, Long>,PointRepositoryCustom {
	public List<PointEntity> getListPoitByScreenEntityAndType(ScreenEntity screenEntity,String type);
	public PointEntity findByXValueAndYValueAndScreenEntityAndType(Integer xValue,Integer yValue,ScreenEntity screenEntity,String type);
}
