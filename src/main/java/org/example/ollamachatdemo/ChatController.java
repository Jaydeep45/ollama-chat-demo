package org.example.ollamachatdemo;


import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {


    final ChatClient chatClient;


    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("chat")
    public  String chat(@RequestBody String prompt) {
        ChatResponse chatResponse = chatClient.call(new Prompt(
                prompt,
                OllamaOptions.create().withModel("gemma:2b").withTemperature(0.4f)
        ));
        return chatResponse.getResult().getOutput().getContent();
    }


}
