package hello;
import java.util.Date;
public class Greeting {

    @Override
	public String toString() {
		return "Greeting [id=" + id + ", content=" + content + ", timestamp=" + timestamp + "]";
	}
	private final long id;
    private final String content;
    private final long timestamp;
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
        this.timestamp = new Date().getTime();
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    public long getTimestamp(){
    	return timestamp;
    }
    
    
}
