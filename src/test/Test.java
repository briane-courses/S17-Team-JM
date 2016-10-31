package test;

import java.util.ArrayList;

import model.User;
import model.UserType;
import service.UserService;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<User> orgReps = UserService.getUsersPerType(UserType.ADMIN);
		User user = null;
		
		for(int i = 0; i < orgReps.size(); i ++) {
			user = orgReps.get(i);
			System.out.print(user.getUserID() + " ");
			System.out.print(user.getEmail() + " \t");
			System.out.println(user.getUserType() + " ");
		}

	}

}
