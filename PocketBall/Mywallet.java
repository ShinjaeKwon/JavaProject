import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Mywallet extends PocketManager{
    private int money;
    private File accountFile = new File("account.txt");

    Scanner sc = new Scanner(System.in);
    public Mywallet(){
        this.money = 0;
    }

    //프로그램이 시작할때 자동으로 가져온다
    void getMoney(){ //계좌->지갑
        try{
            FileReader reader = new FileReader(accountFile);
            BufferedReader buf = new BufferedReader(reader);
            String line;
            while((line = buf.readLine()) != null){
                StringTokenizer tokenizer = new StringTokenizer(line);
                int getMoney = Integer.parseInt(tokenizer.nextToken());
                money += getMoney;
            }
            buf.close();
        } catch (FileNotFoundException e) {
            System.out.println(accountFile + "파일을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //프로그램이 끝날때 자동으로 넣어준다.
    void putMoney(){ //지갑,수익금 -> 계좌
        try{
            FileWriter writer = new FileWriter(accountFile,false);
            BufferedWriter buf = new BufferedWriter(writer);
            buf.write(Integer.toString(money+income));
            buf.close();
        } catch (IOException e) {
            System.out.println(accountFile+"파일에 접근 오류입니다.");
            e.printStackTrace();
        }
    }

    void adminMode(){
        int menu;
        System.out.println("관리자 모드입니다.");
        System.out.println("원하는 메뉴를 선택하세요.");
        System.out.println("1. 지갑에서 가게로 돈 넣기 2. 가게에서 지갑으로 돈 넣기 3. 지갑 잔액 확인하기");
        System.out.print("입력 >>");
        menu =sc.nextInt();
        if(menu == 1){
            depositMoney();
        }else if(menu == 2){
            withdrawMoney();
        }else if(menu == 3){
            showWallet();
        }
        else{
            System.out.println("잘못된 입력입니다.");
        }
    }

    void showWallet(){
        System.out.println("현재 지갑의 잔액은 " +money + "입니다.");
    }

    void depositMoney() { //지갑 -> 가게
        System.out.println("현재 지갑에 가지고 계신 돈은"+money+"원 입니다.");
        System.out.println("이중에서 얼마를 가게에 넣으실껍니까?");
        System.out.print("입력>>");
        while (true) {
            int deposit = sc.nextInt();
            if (deposit > money) {
                System.out.println("수중의 돈보다 가져가실 돈이 더 많습니다. 다시 입력해주세요.");
            }else{
                System.out.println("정상적으로 가게로 넣었습니다.");
                income += deposit;
                money -= deposit;
                break;
            }
        }
        System.out.println("현재 지갑에 가지고 계신 돈은"+money+"원 입니다.");
    }

    void withdrawMoney(){   //가게 -> 지갑
        System.out.println("현재 가게 총 수익금은" + income + "입니다.");
        System.out.println("이중에서 얼마를 가져가실껍니까?");
        System.out.print("입력>>");
        while (true) {
            int deposit = sc.nextInt();
            if (deposit > income) {
                System.out.println("수익금보다 가져가실 돈이 더 많습니다. 다시 입력해주세요.");
            }else{
                System.out.println("정상적으로 가져왔습니다.");
                income -= deposit;
                money += deposit;
                break;
            }
        }
        System.out.println("현재 지갑에 가지고 계신 돈은"+money+"원 입니다.");
    }

}
