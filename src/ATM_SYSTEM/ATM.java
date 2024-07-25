package ATM_SYSTEM;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATM {
    ArrayList<Account> accountList= new ArrayList<>();

    public ATM() {

    }

    //欢迎页
    public void homePage(){
        Scanner sc = new Scanner(System.in);


        try {
            while (true) {
                System.out.println("欢迎进入黑马银行ATM系统");
                System.out.println("1、用户登录");
                System.out.println("2、用户开户");
                System.out.println("3、离开系统");
                switch(sc.nextLine()){
                    case "1":userLogin();
                        break;
                    case "2":addAccount();
                        break;
                    case "3":System.exit(0);
                    default:
                        System.out.println("请输入正确的指令");
                        break;
                       // System.out.println("txt");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //业务办理界面
    public void doBusiness(){
        System.out.println("======办理业务=========");
    }

    //用户登录
    public void userLogin(){
        while (true) {
            if(accountList.isEmpty()){
                System.out.println("系统内没有账户，请先开户");
                return;
            }
            else {
                Scanner sc = new Scanner(System.in);
                System.out.println("请输入用户卡号：");
                String userID = sc.nextLine();
                if(checkId(userID)){
                    System.out.println("请输入密码：");
                    String inputPassword = sc.nextLine();
                    if(checkIdPass(userID,inputPassword)){
                        doBusiness();
                        homePage();
                    }
                    else {
                        continue;
                    }

                }
                else{
                    System.out.println("您输入的卡号不存在，请重新输入");
                    continue;
                }

            }
        }
    }

    //查询卡号是否存在
    public boolean checkId(String id){
        //String idCheck = id;
        for (Account account : accountList) {
            if (id.equals(account.getUserID())) {
                return true;
            }
        }
        return false;
    }

    //校验卡号、密码
    public boolean checkIdPass(String id,String pass){
        for (Account account : accountList) {
            if (id.equals(account.getUserID()) && pass.equals(account.getPassword())) {
                return true;
            }
        }
        System.out.println("用户名或密码输入有误，请重新输入！");
        return false;
    }

    //用户开户
    public void addAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账户名称：");
        String userName = sc.nextLine();
        char gender;

        //性别输入循环
        while (true) {
            System.out.println("请输入您的性别（男/女）：");
            gender = sc.nextLine().charAt(0);
            if(gender != '男' && gender !='女'){
                System.out.println("请输入正确的性别");
                continue;
            }
            else {
                break;
            }
        }

        String validatedPass;
        //密码一致性循环
        while (true) {
            System.out.println("请输入密码：");
            String password = sc.nextLine();
            System.out.println("请再次输入密码：");
            String pass2 = sc.nextLine();
            if(password.equals(pass2)){
                validatedPass = password;
                break;
            }
            else {
                System.out.println("两次密码输入不一致，请重新输入");
            }
        }

        double limitatedAmount;
        //单次取款限额循环
        while (true) {
            System.out.println("请输入账户每次取现额度：");
            try {
                limitatedAmount = Double.parseDouble(sc.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("请输入正确的数额");
            }
        }

        String userID = generateID();
        




        Account acc = new Account(userName,gender,validatedPass,0,limitatedAmount,userID);
        accountList.add(acc);
        System.out.println("恭喜您，开户成功，您的账户卡号为："+userID);

        //测试点，输出所有账户名
        for (Account account : accountList) {
            System.out.println(account.getUserID()+"\t");
        }


    }
    
    //返回不重复的ID
    public String generateID(){
        Random r = new Random();
        StringBuilder userID = new StringBuilder();
        boolean continueGen=true;
        boolean ifFound = false;
        
        while (continueGen) {
            System.out.println("开始生成ID");
            userID = new StringBuilder();
            for(int i=0;i<5;i++){
                userID.append(r.nextInt(10));
            }
            for (Account account : accountList) {
                if (account.getUserID().contentEquals(userID)) {
                    ifFound = true; //表示list非空
                    break;
                } else {
                    continueGen = false;
                }
            }

            //如果list为空，则id必然不会重复，跳出while循环
            if (!ifFound) {
                continueGen = false;
            }
            
            
        }
        return userID.toString();
        
    }


}
