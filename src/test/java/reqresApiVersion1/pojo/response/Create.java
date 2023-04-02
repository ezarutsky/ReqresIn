package reqresApiVersion1.pojo.response;

public class Create {
    public String name;
    public String job;
    public String id;
    public String createdAt;

    public Create() {
    }

    public Create(String name, String job, String id, String createdAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }


}
