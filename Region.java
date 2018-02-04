import java.util.ArrayList;

public class Region {
    private ArrayList<Integer> cells;
    Region(){
        cells = new ArrayList<>();
    }
    public void addNumber(Cell cell) throws Exception{
        Integer value = cell.getValue();
        if(cells.contains(value)){
            throw new Exception("Region already contains a "+value+"!");
        }else{
            cells.add(value);
        }
    }
    public void removeNumber(Integer value){
        int toRemove=-1;
        for(int i=0;i<cells.size();i++){
            if(cells.get(i).equals(value)){
                toRemove=i;
            }
        }
        if(toRemove!=-1) {
            cells.remove(toRemove);
        }
    }
    public boolean hasNumber(Integer number){
        return cells.contains(number);
    }
}