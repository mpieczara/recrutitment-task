package com.task.recruitment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Data {

    @Id
    private long id;

    private String name;

    private String description;

    private LocalDate timestamp;

    public Data(long id, String name, String description, LocalDate timestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Data() {}

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;

        Data data = (Data) o;

        if (id != data.id) return false;
        if (!getName().equals(data.getName())) return false;
        if (getDescription() != null ? !getDescription().equals(data.getDescription()) : data.getDescription() != null)
            return false;
        return getTimestamp().equals(data.getTimestamp());
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getTimestamp().hashCode();
        return result;
    }
}
