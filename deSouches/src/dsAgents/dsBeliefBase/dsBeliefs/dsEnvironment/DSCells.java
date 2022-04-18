package dsAgents.dsBeliefBase.dsBeliefs.dsEnvironment;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class DSCells {
    LinkedList<DSCell> PCells;

    public LinkedList<DSCell> getCells(){
        return(PCells);
    }

    protected int getSize(){
        return(PCells.size());
    }

    // top left corner
    protected Point getTLC(){
        if (PCells.isEmpty())
                return null;
        Point tlc =PCells.getFirst().getPosition();
        for(DSCell cell:PCells) {
            if (cell.getX() <= tlc.x)
                tlc.x=cell.getX();
            if (cell.getY() <= tlc.y)
                tlc.y=cell.getY();
        }
            return(tlc);
    }
// bottom right corner
    protected Point getBRC() {
        if (PCells.isEmpty())
            return null;
        Point brc = PCells.getFirst().getPosition();
        for (DSCell cell : PCells){
            if (cell.getX() >= brc.x)
                brc.x = cell.getX();
        if (cell.getY() >= brc.y)
            brc.y = cell.getY();
        }
        return(brc);
    }


    synchronized protected void put(DSCell element){
        PCells.add(element);
    }



    synchronized protected void removeOlder(Point point, int step, boolean removeArea) {
        LinkedList<DSCell> newList=new LinkedList<DSCell>();
        for(DSCell element:PCells)
            if((element.getPosition().x!=point.x)||(element.getPosition().y!=point.y)||(element.getTimestamp()>step)
                    ||(((element.getType()==DSCell.__DSGoal)||(element.getType()==DSCell.__DSRoleArea))&&(!removeArea)))
                    newList.add(element);
            PCells=newList;
    }

        public void removeCell(int x, int y, int type){
        LinkedList<DSCell> newList=new LinkedList<DSCell>();
        for(DSCell element:PCells)
            if((element.getPosition().x!=x)||(element.getPosition().y!=y)||(element.getType()!=type))
                newList.add(element);

        PCells=newList;
    }

    public LinkedList<DSCell> getInRange(int vision){
        LinkedList<DSCell> newList=new LinkedList<DSCell>();
        for(DSCell element:PCells)
            if(Math.abs(element.getPosition().x)+Math.abs(element.getPosition().y)<=vision)
                newList.add(element);

            return(newList);
    }

    public DSCell getFirst(Point point){
        LinkedList<DSCell> newList=new LinkedList<DSCell>();
        for(DSCell element:PCells)
            if((element.getPosition().x==point.x)&&(element.getPosition().y==point.y))
                return(element);
            return(null);
    }

    public LinkedList<DSCell> getAllAt(Point point){
        LinkedList<DSCell> cells=new LinkedList<DSCell>();
        for(DSCell element:PCells)
            if((element.getPosition().x==point.x)&&(element.getPosition().y==point.y))
                cells.add(element);
        return(cells);
    }

    public DSCell getKeyType(Point point,int type){
        for(DSCell element:PCells)
            if((element.getPosition().x==point.x)&&(element.getPosition().y==point.y)&&(element.getType()==type))
                return(element);
        return(null);

    }

    public LinkedList<DSCell> getAllType(int type){
        LinkedList<DSCell> cells=new LinkedList<DSCell>();
        for(DSCell element:PCells)
            if(element.getType()==type)
                cells.add(element);
        return(cells);
    }

    public boolean containsKey(Point point){
        for(DSCell element:PCells)
            if((element.getPosition().x==point.x)&&(element.getPosition().y==point.y))
                return(true);

            return(false);
    }

    public DSCells(){
        PCells=new LinkedList<DSCell>();
    }
}
