package �ֲ������ҵ;

public class GradeDaoImpl extends BaseDao implements GradeDao {

	@Override
	public int addGrade(int gid,String name) {
		String sql = "insert into grade values(?,?)";
		return this.executeUpdate(sql, new Object [] {gid,name});
	}

}
