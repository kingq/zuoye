package �ֲ������ҵ;

import java.util.Scanner;

public class ShowGrade {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("�������꼶��ţ�");
		int gid = input.nextInt();
		System.out.println("�������꼶���ƣ�");
		String name = input.next();
		GradeService gs = new GradeServiceImpl();
		int temp = gs.addGrade(gid, name);
		if(temp>0) {
			System.out.println("����꼶�ɹ���");
		}else {
			System.out.println("����꼶ʧ��");
		}
	}
}
