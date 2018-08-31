package ru.andreymarkelov.atlas.plugins.ganttlite.servlet;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.templaterenderer.TemplateRenderer;

public class GanttServlet extends HttpServlet {
    private final LoginUriProvider loginUriProvider;
    private final JiraAuthenticationContext jiraAuthenticationContext;
    private final TemplateRenderer templateRenderer;

    public GanttServlet(
            LoginUriProvider loginUriProvider,
            JiraAuthenticationContext jiraAuthenticationContext,
            TemplateRenderer templateRenderer) {
        this.loginUriProvider = loginUriProvider;
        this.jiraAuthenticationContext = jiraAuthenticationContext;
        this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        ApplicationUser applicationUser = jiraAuthenticationContext.getLoggedInUser();
        if (applicationUser == null) {
            redirectToLogin(httpServletRequest, httpServletResponse);
            return;
        }

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("lang", httpServletRequest.getLocale().getLanguage());
        parameters.put("baseUrl", getBaseUrl(httpServletRequest));
        parameters.put("i18n", jiraAuthenticationContext.getI18nHelper());

        httpServletResponse.setContentType("text/html;charset=utf-8");
        templateRenderer.render(
                "/ru/andreymarkelov/atlas/plugins/ganttlite/templates/ganttlite.vm",
                parameters,
                httpServletResponse.getWriter());
    }

    private URI getUri(HttpServletRequest request) {
        StringBuffer builder = request.getRequestURL();
        if (request.getQueryString() != null) {
            builder.append("?");
            builder.append(request.getQueryString());
        }
        return URI.create(builder.toString());
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(loginUriProvider.getLoginUri(getUri(request)).toASCIIString());
    }

    private String getBaseUrl(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath();
    }
}
