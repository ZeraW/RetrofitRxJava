package gmsproduction.com.retrofitrxjava.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hima on 12/17/2018.
 */

public class Users {

    @SerializedName("name")
    String name;
    @SerializedName("job")
    String job;

    public String getName() {
        return name;
    }
    public String getJob() {
        return job;
    }
}
