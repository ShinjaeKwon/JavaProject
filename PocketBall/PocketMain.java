import java.util.Scanner;

public class PocketMain {
    public static void main(String[] args) {
        PocketManager manager = new PocketManager();
        Mywallet wallet = new Mywallet();
        Scanner sc = new Scanner(System.in);
            int table;
            System.out.println("사용하실 테이블 갯수를 입력해주세요.");
            System.out.print("입력 >>");
            table = sc.nextInt();
            manager.setTable(table);
            wallet.getMoney();
        while (true) {
            int menu;
            System.out.println("******당구장 관리 프로그램 입니다.******");
            System.out.println("1) 테이블사용");
            System.out.println("2) 테이블사용종료");
            System.out.println("3) 자리옮기기");
            System.out.println("4) 시간경과");
            System.out.println("5) 검색");
            System.out.println("6) 전체로그출력");
            System.out.println("7) 관리자모드");
            System.out.println("0) 종 료");
            System.out.println();
            System.out.print("메뉴 >> ");
            menu = sc.nextInt();
            if(menu == 1) manager.useTable();
            else if(menu == 2) manager.bill();
            else if(menu == 3) manager.moveTable();
            else if(menu == 4) manager.addTime();
            else if(menu == 5) manager.search();
            else if(menu == 6) manager.showLog();
            else if(menu == 7) wallet.adminMode();
            else if(menu == 0){
                System.out.println("당구장 관리 프로그램을 종료합니다.");
                wallet.putMoney();
                break;
            }
            else{
                System.out.println("메뉴 선택이 잘못됬습니다. 다시 입력해 주세요.");
            }
        }
    }
}