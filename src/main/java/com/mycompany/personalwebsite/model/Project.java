package com.mycompany.personalwebsite.model;

import java.sql.Date;

public class Project {
    private int id;
    private int week;
    private String title;
    private String description;
    private String techStack; // Stored as "HTML,CSS"
    private String demoLink;
    private String repoLink;
    private String thumbnail;
    private Date createdAt;

    public Project() {}

    public Project(int week, String title, String description, String techStack, String demoLink, String repoLink) {
        this.week = week;
        this.title = title;
        this.description = description;
        this.techStack = techStack;
        this.demoLink = demoLink;
        this.repoLink = repoLink;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getWeek() { return week; }
    public void setWeek(int week) { this.week = week; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTechStack() { return techStack; }
    public void setTechStack(String techStack) { this.techStack = techStack; }

    public String getDemoLink() { return demoLink; }
    public void setDemoLink(String demoLink) { this.demoLink = demoLink; }

    public String getRepoLink() { return repoLink; }
    public void setRepoLink(String repoLink) { this.repoLink = repoLink; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}

