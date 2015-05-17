package client.driver.dto;

import java.time.LocalTime;
import java.util.UUID;

import org.springframework.util.Assert;

public class MicroClientServiceDTO {
    private LocalTime time = LocalTime.now();
    private UUID uuid = UUID.randomUUID();

    public MicroClientServiceDTO() {
        super();
    }

    public MicroClientServiceDTO(String time, String uuid) {
        super();
        this.time = LocalTime.parse(time);
        this.uuid = UUID.fromString(uuid);
    }

    public LocalTime getTime() {
        Assert.notNull(this.time);
        return time;
    }

    public void setTime(String time) {
        this.time = LocalTime.parse(time);
    }

    public UUID getUUID() {
        Assert.notNull(this.uuid);
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }
}
