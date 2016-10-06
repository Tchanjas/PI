package BBP;

import java.math.BigDecimal;

public class SharedBigDecimal {
    volatile BigDecimal sharedValue;
    
    public SharedBigDecimal(double value){
        this.sharedValue = BigDecimal.valueOf(value);
    }
    public synchronized BigDecimal getValue(){
        return sharedValue;
    }
    public synchronized void add(BigDecimal value){
        sharedValue = sharedValue.add(value);
    }
    
    public BigDecimal getVal(){
        return sharedValue;
    }
}
