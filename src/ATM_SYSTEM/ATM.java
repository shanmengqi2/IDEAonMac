package ATM_SYSTEM;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    ArrayList<Account> accountList= new ArrayList<>();

    public ATM() {

    }

    //欢迎页
    public void homePage(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("欢迎进入黑马银行ATM系统");
            System.out.println("1、用户登录");
            System.out.println("2、用户开户");
            switch(sc.nextLine()){
                case "1":userLogin();
                    break;
                case "2":addAccount();
                    break;
                default:
                    System.out.println("请输入正确的指令");
            }
        }
    }

    //用户登录
    public void userLogin(){

    }

    //用户开户
    public void addAccount(){

    }


}
