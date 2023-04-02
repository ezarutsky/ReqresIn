package reqresApiVersion3.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUpdate {
    public String name;
    public String job;
    public String updatedAt;

//
//    public ResponseUpdate() {
//    }
//
//    public ResponseUpdate(String name, String job, String updatedAt) {
//        this.name = name;
//        this.job = job;
//        this.updatedAt = updatedAt;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getJob() {
//        return job;
//    }
//
//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setJob(String job) {
//        this.job = job;
//    }
//
//    public void setUpdatedAt(String updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//

}
