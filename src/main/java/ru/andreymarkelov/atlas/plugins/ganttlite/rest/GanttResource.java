package ru.andreymarkelov.atlas.plugins.ganttlite.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.security.JiraAuthenticationContext;

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


        CacheControl cc = new CacheControl();
        cc.setNoCache(true);
        cc.setNoStore(true);
        cc.setMaxAge(0);
        return Response.ok("[]").cacheControl(cc).build();
    }
}
