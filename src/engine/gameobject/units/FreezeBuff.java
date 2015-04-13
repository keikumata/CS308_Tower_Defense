package engine.gameobject.units;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;

public class FreezeBuff extends Buff{
    
    public FreezeBuff(int duration){
        super(duration);
    }
    
    public void apply(BuffableUnit myUnit){
        myUnit.getMover().setFreeze(true);
        adjustEffect(myUnit, .66, .5, .5, .5);
    }
    
    public void unapply(BuffableUnit myUnit){
        myUnit.getMover().setFreeze(false);
        adjustEffect(myUnit, -.66, -.5, -.5, -.5);
    }
    
    public boolean isStrongerBuff(Buff otherBuff){
        return otherBuff.timeLeft() <= timeLeft();
    }
    
    public Buff clone(){
        return new FreezeBuff(getDuration());
    }
}
