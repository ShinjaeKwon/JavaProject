public class PocketBall {
    private int time;
    private int tmm; //10분 요금
    private boolean onoff;

    public PocketBall (){
        time = 0;
        tmm = 1800;
        onoff = false;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int addtime) {
        this.time += addtime;
    }
    public void setzTime() {
        time = 0;
    }

    public int getTmm() {
        return tmm;
    }

    public void setTmm(int tmm) {
        this.tmm = tmm;
    }

    public boolean getOnoff() {
        return onoff;
    }

    public void setOnoff( ) {
        onoff = !onoff;
    }

    public int getcharge(){
        int charge = 0;
        while(true){
            if(time >10){
                time -=10;
                charge += tmm;
            }
            else if(time <=0){
                break;
            }
            else if(time <= 10){
                time = 0;
                charge += tmm;
            }
        }
        return charge;
    }
}
