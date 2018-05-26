package model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.RecycleMaterial;
import util.DatabaseUtil;

public class MaterialModel
{
	public List<RecycleMaterial> getAllMaterial() 
	{
		List<RecycleMaterial> ListRecycleMaterial = new ArrayList<RecycleMaterial>();
		Connection conn = DatabaseUtil.getConnection();
		
		CallableStatement callSt = null;
		ResultSet rs = null;
		try 
		{
			String sql = "select * from boonting.recycle_material";
			callSt = conn.prepareCall(sql);
			System.out.println("sql: " + callSt.toString());
			
			rs = callSt.executeQuery();
			while(rs.next()) {
				RecycleMaterial recycleMaterial = new RecycleMaterial();
				recycleMaterial.setRmId(rs.getInt(1));
				recycleMaterial.setRmName(rs.getString(2));
				recycleMaterial.setRmDescription(rs.getString(3));
				recycleMaterial.setRmPrice(rs.getFloat(4));
				recycleMaterial.setRmImage(rs.getString(5));
				ListRecycleMaterial.add(recycleMaterial);
			}
		} 
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		} finally {
			DatabaseUtil.close(callSt, rs, conn);
		}
		return ListRecycleMaterial;
	}
}
