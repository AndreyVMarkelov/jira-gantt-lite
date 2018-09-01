package ru.andreymarkelov.atlas.plugins.ganttlite.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task {
    @XmlElement
    private String id;
    @XmlElement
    private String name;
    @XmlElement
    private String start;
    @XmlElement
    private String end;
    @XmlElement
    private String progress;
    @XmlElement
    private String dependencies;
    @XmlElement
    private String custom_class;

    public Task() {
    }

    public Task(String id, String name, String start, String end, String progress, String dependencies, String custom_class) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.progress = progress;
        this.dependencies = dependencies;
        this.custom_class = custom_class;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }

    public String getCustom_class() {
        return custom_class;
    }

    public void setCustom_class(String custom_class) {
        this.custom_class = custom_class;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", progress='" + progress + '\'' +
                ", dependencies='" + dependencies + '\'' +
                ", custom_class='" + custom_class + '\'' +
                '}';
    }
}
