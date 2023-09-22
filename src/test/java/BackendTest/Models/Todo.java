package BackendTest.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Todo {

    public Todo()
    {

    }
public Todo(boolean isCompleted,String item)
{
this.isCompleted=isCompleted;
this.item=item;
}

public Todo (String item)
{
    this.item=item;
}


//Default value for this Boolean is Null
    @JsonProperty("isCompleted")
    private Boolean isCompleted;

    @JsonProperty("_id")
    private String id;

    @JsonProperty("item")
    private String item;
    @JsonProperty("userID")
    private String userID;
    private String createdAt;
    @JsonProperty("__v")
    private String v;


    @JsonProperty("isCompleted")
    public Boolean getIsCompleted() {
        return isCompleted;
    }

    @JsonProperty("isCompleted")
    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(String item) {
        this.item = item;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("__v")
    public String getV() {
        return v;
    }

    @JsonProperty("__v")
    public void setV(String v) {
        this.v = v;
    }



}
