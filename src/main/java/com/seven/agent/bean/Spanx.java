package com.seven.agent.bean;


import java.util.Date;

public class Spanx {
    private Tags tags;
    private String type;
    private Date time;
    private String pid;
    private String gid;
    private String id;
    private Long spend;

    public Spanx(String spanType){
        setType(spanType);
        setTime(new Date());
    }

    public void fillEnvInfo(){
    }

    public Object getTag(String key){
        if(tags == null){
            return null;
        }
        return tags.get(key);
    }

    public Spanx addTag(String key,Object val) {
        if(tags == null){
            tags = new Tags();
        }
        this.tags.put(key,val);
        return this;
    }

    public Spanx removeTag(String key){
        if(tags != null){
            this.tags.remove(key);
        }
        return this;
    }

    public String getType() {
        return type;
    }

    public Spanx setType(String type) {
        this.type = type;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public Spanx setTime(Date time) {
        this.time = time;
        return this;
    }



    public String getPid() {
        return pid;
    }

    public Spanx setPid(String pid) {
        this.pid = pid;
        return this;
    }

    public String getGid() {
        return gid;
    }

    public Spanx setGid(String gid) {
        this.gid = gid;
        return this;
    }

    public String getId() {
        return id;
    }

    public Spanx setId(String id) {
        this.id = id;
        return this;
    }

    public Long getSpend() {
        return spend;
    }

    public void setSpend(Long spend) {
        this.spend = spend;
    }


    public Tags getTags() {
        return tags;
    }

}
