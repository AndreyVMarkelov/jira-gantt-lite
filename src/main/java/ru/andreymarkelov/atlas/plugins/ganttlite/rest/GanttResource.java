package ru.andreymarkelov.atlas.plugins.ganttlite.rest;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.security.JiraAuthenticationContext;
import ru.andreymarkelov.atlas.plugins.ganttlite.model.Task;

@Path("/gantt")
public class GanttResource {
    private final JiraAuthenticationContext jiraAuthenticationContext;

    public GanttResource(JiraAuthenticationContext jiraAuthenticationContext) {
        this.jiraAuthenticationContext = jiraAuthenticationContext;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    @Path("/events")
    public Response getEvents(@Context HttpServletRequest httpServletRequest) {
        List<Task> taskList = new ArrayList<>();

        Task task = new Task();
        task.setId("Task 1");
        task.setName("Deploy");
        task.setStart("2018-08-10");
        task.setEnd("2018-09-02");
        task.setProgress("20");
        task.setDependencies("");
        task.setCustom_class("bar-milestone");

        taskList.add(task);

        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setNoStore(true);
        cc.setMaxAge(0);
        return Response.ok(taskList).cacheControl(cc).build();
    }
}
