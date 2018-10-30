package 分层设计作业;

import java.util.Scanner;

public class ShowGrade {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入年级编号：");
		int gid = input.nextInt();
		System.out.println("请输入年级名称：");
		String name = input.next();
		GradeService gs = new GradeServiceImpl();
		int temp = gs.addGrade(gid, name);
		if(temp>0) {
			System.out.println("添加年级成功！");
		}else {
			System.out.println("添加年级失败");
		}
	}
}
