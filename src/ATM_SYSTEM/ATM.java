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
    @SuppressWarnings("InfiniteLoopStatement")
    public void doBusiness(String userID){
        Scanner sc = new Scanner(System.in);
        String userName = "";
        for (Account account : this.accountList) {
            if (account.getUserID().equals(userID)) {
                userName = account.getUserName();
            }
        }

        while (true) {
            System.out.println("======欢迎 "+userName+" 办理业务=======");
            System.out.println("1、查询账户");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、修改密码");
            System.out.println("6、退出");
            System.out.println("7、注销账户");

            switch(sc.nextLine()){
                case "1":accountInfo(userID);
                break;
                case "2":deposit(userID);
                break;
                case "3":withdraw(userID);
                break;
                case "4":transfer(userID);
                break;
                case "5":changePassword(userID);
                break;
                case "6":homePage();
                break;
                case "7":closeAccount(userID);
                break;
                default:
                    System.out.println("请输入正确的序号");
            }
        }


    }

    //通过ID获取账户对象
    public Account getAccountByUserID(String userID){
        for(Account account : this.accountList){
            if(account.getUserID().equals(userID)){
                return account;
            }
        }
        return null;
    }

    //用户信息查询
    public void accountInfo(String userID){

        Account acc=getAccountByUserID(userID);

        System.out.println("+++++++++当前账户信息如下++++++++++");
        System.out.println("卡号："+userID);
        System.out.println("户主："+acc.getUserName());
        System.out.println("性别："+acc.getGender());
        System.out.println("余额："+acc.getBalance());
        System.out.println("限额："+acc.getLimitation());
    }



    //存款
    public void deposit(String userID){
        Scanner sc = new Scanner(System.in);
        Account acc=getAccountByUserID(userID);

        double deposit;

        System.out.println("=======用户存款操作========");
        System.out.println("请输入存款金额：");
        //this.accountList.remove(acc);
        deposit = Double.parseDouble(sc.nextLine());
        acc.setBalance(acc.getBalance()+deposit);
        //this.accountList.add(acc);
        System.out.println("成功存款 "+deposit+"元");

    }

    //取款
    public void withdraw(String userID){
        Scanner sc = new Scanner(System.in);
        Account acc=getAccountByUserID(userID);
        double balance=acc.getBalance();

        while (true) {
            System.out.println("请输入取款金额：");
            double withdraw=Double.parseDouble(sc.nextLine());
            if(withdraw>balance){
                System.out.println("余额不足，您当前余额为："+balance+"元");
            } else if (withdraw>acc.getLimitation()) {
                System.out.println("超出取款限额，您目前最多可取："+acc.getLimitation()+"元");
            }
            else {
                acc.setBalance(balance-withdraw);
                System.out.println("成功取款"+withdraw+"元"+"，当前余额为："+acc.getBalance());
                break;
            }
        }
    }

    //转账
    public void transfer(String userID){
        Scanner sc = new Scanner(System.in);
        boolean flag=true;
        while (flag) {
            if(this.accountList.size()<2){
                System.out.println("当前系统中，不足2个账户，请去开户吧");
                break;
            }

            Account acc=getAccountByUserID(userID);
            System.out.println("请您输入对方账户的卡号：");
            String inputId = sc.nextLine();
            if(getAccountByUserID(inputId) == null){
                System.out.println("您输入的卡号不存在，请重新输入");
            }
            else{
                Account acc2=getAccountByUserID(inputId);
                String nameWithStar = acc2.getUserName().replace(acc2.getUserName().charAt(0),'*');
                System.out.println("请您输入"+nameWithStar+"的姓氏：");
                char lastName = sc.nextLine().charAt(0);
                if(lastName==acc2.getUserName().charAt(0)){
                    while (true) {
                        System.out.println("请输入转账金额：");
                        double transfer = Double.parseDouble(sc.nextLine());
                        if(transfer>acc.getBalance()){
                            System.out.println("余额不足，您目前最多可转："+acc.getBalance()+"元");
                        }
                        else{
                            acc.setBalance(acc.getBalance()-transfer);
                            acc2.setBalance(acc2.getBalance()+transfer);
                            System.out.println("转账成功，您当前的账户余额为："+acc.getBalance()+"元");
                            flag = false;
                            break;
                        }
                    }
                }
                else{
                    System.out.println("您输入的姓氏有误");
                }
            }
        }
    }

    //修改密码
    public void changePassword(String userID){
        Scanner sc = new Scanner(System.in);
        Account acc=getAccountByUserID(userID);
        String passWord = acc.getPassword();
        boolean flag=true;
        System.out.println("=======用户密码修改========");
        while (flag) {

            System.out.println("请输入当前密码：");
            String inputPassword = sc.nextLine();
            if(inputPassword.equals(passWord)){
                while (true) {
                    System.out.println("请输入新密码：");
                    String newPassWord = sc.nextLine();
                    System.out.println("请再次输入密码：");
                    String newPassWord2 = sc.nextLine();
                    if(newPassWord.equals(newPassWord2)){
                        acc.setPassword(newPassWord);
                        System.out.println("密码修改成功！");
                        flag = false;
                        break;
                    }
                    else {
                        System.out.println("两次密码输入不一致，请重新输入");
                    }
                }
            }
            else {
                System.out.println("您输入的密码有误，请重新输入");
            }
        }
    }

    //注销账户
    public void closeAccount(String userID){
        Scanner sc = new Scanner(System.in);
        System.out.println("============用户销户===========");
        System.out.println("您真的要销户吗？y确认，其它任意键取消");
        char inputChar = sc.nextLine().charAt(0);
        if(inputChar!='y'){
            System.out.println("当前账户继续保留");
        }
        else
        {
            Account acc=getAccountByUserID(userID);
            if(acc.getBalance()>0){
                System.out.println("账户尚有余额，不允许销户");
            }
            else{
                this.accountList.remove(acc);
                System.out.println("您的账户销户完成");
                homePage();
            }
        }
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
                        doBusiness(userID);
                        homePage();
                    }
                }
                else{
                    System.out.println("您输入的卡号不存在，请重新输入");
                    //continue;
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
               // continue;
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

        //测试点，输出所有账户卡号
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
