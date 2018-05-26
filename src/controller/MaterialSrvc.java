package controller;
import java.util.List;
import dto.MaterialResp;
import dto.RecycleMaterial;
import model.MaterialModel;
public class MaterialSrvc {

	public MaterialSrvc()
	{
		
	}
	
	public MaterialResp getAllMaterial()
    {	
		MaterialResp materialResp = new MaterialResp();
		MaterialModel material = new MaterialModel();
		List<RecycleMaterial> RecycleMaterialList = material.getAllMaterial();
		materialResp.setRecords(RecycleMaterialList);
		materialResp.setTotalRecords(RecycleMaterialList.size());
		return materialResp;
    }
}
