package com.WebHooks.Controller;

import com.WebHooks.Model.PushEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class webHookController
{
    private Logger logger= LoggerFactory.getLogger(webHookController.class);


    @PostMapping("/github")
    public ResponseEntity<?> handleGithub(@RequestHeader("X-GitHub-Event") String eventType, @RequestBody PushEvent pushEvent)
    {
        logger.info("📦 Received GitHub event: {}", eventType);
        if ("push".equals(eventType) && pushEvent != null) {
            logger.info("📍 Repo: {}", pushEvent.getRepository().getFull_name());
            logger.info("👤 Pusher: {}", pushEvent.getPusher().getName());
        }
        return ResponseEntity.ok("✅ Webhook received!");
    }
}
