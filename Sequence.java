
public class Sequence extends LList {
	SType type;
	LList list;
    enum SType{
        DNA, RNA, EMPTY
    }
    public Sequence(SType type, LList list){
        this.type = type;
        this.list = list;   	
    }
    public Sequence(SType type) {
    	this.type = type;
    }
    public Sequence() {	
    }
    public SType getType() {
    	return type;
    }
    public String getList() {
    	 return formatList(list);
    }
    public void setType(SType type) {
    	this.type = type;
    }
    public void setList(LList list) {
    	this.list = list;
    }
}
