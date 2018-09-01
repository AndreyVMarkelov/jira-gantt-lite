AJS.toInit(function () {
    var tasks = [
        {
            id: 'Task First',
            name: 'Redesign website',
            start: '2018-08-10',
            end: '2018-08-15',
            progress: 20,
            dependencies: 'Task 1',
            custom_class: 'bar-milestone' // optional
        }
    ];

    var gantt = new Gantt(
        ".gantt-lite",
        tasks,
        {
            header_height: 50,
            column_width: 30,
            step: 24,
            view_modes: ['Quarter Day', 'Half Day', 'Day', 'Week', 'Month'],
            bar_height: 20,
            bar_corner_radius: 3,
            arrow_curve: 5,
            padding: 18,
            view_mode: 'Day',
            date_format: 'YYYY-MM-DD',
            custom_popup_html: null
        });

    AJS.$.ajax({
        url: AJS.contextPath() + "/rest/ganttlitews/1.0/gantt/events",
        type: "GET",
        contentType: "text/plain",
        processData: false,
        success: function (e) {
            AJS.$.each(e, function (index, value) {
                tasks.push(value);
            });
            gantt.refresh(tasks);
        },
        error: function (error) {
            if (error.responseText) {
                alert(error.responseText);
            }
        }
    });
});
