package app.web;

import app.tasks.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private ScheduledTasks scheduledTasks;

    @RequestMapping("/")
    @ResponseBody
    public String home() {

        return String.format("Scheduled for: %s", scheduledTasks.getTimes());
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update() {

        scheduledTasks.fetchPrayerTimes();
        return String.format("Scheduled for: %s", scheduledTasks.getTimes());
    }
}
