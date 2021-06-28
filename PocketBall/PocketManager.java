import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PocketManager {
    Scanner sc = new Scanner(System.in);

    private int tableNum; //총 테이블 개수
    protected static int income; //총수입
    private PocketBall tables[];
    private File incomefile = new File("income.txt");

    public void setTable(int input){
        tableNum = input;
        tables= new PocketBall[input];
        int income = 0;
        for(int i=0; i<tables.length;i++) tables[i] = new PocketBall();
    }

    public void useTable(){
        while(true){
            int input;
            System.out.println("사용하실 테이블 번호를 입력해주세요"+tableNum+"번까지 이용가능합니다.");
            System.out.print("입력 >>");
            input = sc.nextInt();
            if(input > tableNum) {
                System.out.println("테이블 번호를 다시 입력해주세요.");
                continue;
            }
            if(!tables[input - 1].getOnoff()){
                System.out.println(input+"번 테이블 이용을 시작합니다.");
                tables[input-1].setOnoff();
                break;
            } else {
                System.out.println("이미 사용중인 테이블입니다. 다른 테이블을 이용해주세요");
            }
        }
    }
    public void bill(){
        System.out.println("계산할 테이블 번호를 입력해주세요."+tableNum+"번 테이블 까지 있습니다.");
        int input;
        System.out.print("입력 >>");
        input = sc.nextInt();
        int tempchar;
        if(tables[input - 1].getOnoff()){
            System.out.println(input+"번 테이블을 계산합니다.");
            tempchar = tables[input-1].getcharge();
            income += tempchar;
            System.out.println(input+"번 테이블 요금은 "+tempchar+"원입니다.");
            System.out.println("총수익 : "+income);
            tables[input-1].setOnoff();
            int n = tempchar;
           try{
               if(!incomefile.isFile()){
                   FileWriter writer = new FileWriter(incomefile);
                   BufferedWriter buf = new BufferedWriter(writer);
                   buf.write(Integer.toString(input)+",");
                   buf.write(Integer.toString(n));
                   buf.newLine();
                   buf.close();
               }else{
                   FileWriter writer = new FileWriter(incomefile,true);
                   BufferedWriter buf = new BufferedWriter(writer);
                   buf.write(Integer.toString(input)+",");
                   buf.write(Integer.toString(n));
                   buf.newLine();
                   buf.close();
               }
           } catch (IOException e) {
               System.out.println(incomefile+"파일에 접근 오류입니다.");
               e.printStackTrace();
           }

        } else {
            System.out.println("그 테이블은 사용하고 있지 않습니다.");
        }
    }
    public void addTime(){
        System.out.println("몇분 경과 시킬 까요?");
        int input;
        System.out.print("입력 >>");
        input =  sc.nextInt();
        System.out.println(input+"분 경과했습니다.");
        for(int i=0; i< tableNum; i++){
            if(tables[i].getOnoff()){
                tables[i].setTime(input);
            }
        }
    }

    public void search(){
        System.out.println("1. 수익 총합 보기 2. 전체 테이블 상태 확인 3. 특정 테이블 확인");
        int input;
        System.out.print("입력 >>");
        input = sc.nextInt();

        if(input == 1){
            System.out.println("총 수익 : "+income);
        } else if(input == 2){
            for(int i=0; i<tableNum;i++){
                System.out.print((i+1)+"번 테이블");
                if(tables[i].getOnoff()){
                    System.out.println("사용중, 사용시간 : "+tables[i].getTime());
                }
                else {
                    System.out.println("미사용중");
                }
            }
        } else if(input ==3){
            System.out.println("확인할 테이블 번호를 입력해주세요."+tableNum+"번 테이블 까지 있습니다.");
            System.out.print("입력 >>");
            input = sc.nextInt();
            System.out.print(input+"번 테이블 ");
            if(tables[input - 1].getOnoff()){
                System.out.println("사용중, 사용시간 : "+tables[input-1].getTime());
            }else{
                System.out.println("미사용중");
            }
        }else{
            System.out.println("메뉴를 다시 입력해 주세요.");
        }
    }
    public void moveTable(){
        System.out.println("이동할 테이블 번호를 입력해주세요."+tableNum+"번 테이블까지 있습니다.");
        int input;
        System.out.print("입력 >>");
        input = sc.nextInt();
        int input2;
        if(tables[input - 1].getOnoff()){
            System.out.println("이동시킬 테이블 번호를 입력해주세요.");
            System.out.print("입력 >>");
            input2 = sc.nextInt();
            if(tables[input2 - 1].getOnoff()){
                System.out.println("이미 이 테이블은 사용중입니다.");
            } else{
                System.out.println(input+"번 테이블을 "+input2+"번 테이블로 이동시켰습니다.");
                tables[input2-1].setOnoff();
                tables[input2-1].setTime(tables[input-1].getTime());
                tables[input -1].setzTime();
                tables[input-1].setOnoff();
            }
        }else{
            System.out.println("현재 이 테이블은 미사용 상태입니다.");
        }
    }

    public void showLog(){
        try{
            FileReader reader = new FileReader(incomefile);
            BufferedReader buf = new BufferedReader(reader);
            String line;
            while((line = buf.readLine()) != null){
                StringTokenizer tokenizer = new StringTokenizer(line,",");
                int tableNumber = Integer.parseInt(tokenizer.nextToken());
                int incoming = Integer.parseInt(tokenizer.nextToken());
                System.out.println("테이블 사용 번호 : "+tableNumber+", 결제금 : "+incoming);
            }
            buf.close();
        } catch (FileNotFoundException e) {
            System.out.println(incomefile + "파일을 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
