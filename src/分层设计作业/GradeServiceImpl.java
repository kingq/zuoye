package 分层设计作业;

public class GradeServiceImpl implements GradeService {

	@Override
	public int addGrade(int gid, String name) {
		// TODO Auto-generated method stub
		GradeDao gDao = new GradeDaoImpl();
		return gDao.addGrade(gid, name);
	}

}
