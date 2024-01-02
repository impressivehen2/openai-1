# ChatGPT - Spring [INCOMPLETE - NOT TESTED API KEY]
#### Last Update - 01/01/2024
## Links
(a) [Tutorial](https://levelup.gitconnected.com/chatgtp-integration-with-spring-boot-application-280637e69e73)

(b) [openai API reference](https://platform.openai.com/docs/api-reference/introduction)

(c) [openai Tokenizer](https://platform.openai.com/tokenizer)
tokens: Common sequences of characters found in a set of text. A helpful rule of thumb is that one token generally corresponds to ~4 characters of text for common English text. This translates to roughly ¾ of a word (so 100 tokens ~= 75 words).

(d) [Spring AI Tutorial](https://medium.com/gitconnected/add-generative-ai-in-your-spring-boot-application-spring-ai-12561b1adf08)

## Summary
Call openai API

## Knowledge
(a) "prompt"
prompt refers to the input provided user to generate a response

(b) openai endpoint - Chat
```POST https://api.openai.com/v1/chat/completions```
Given a list of messages comprising a conversation, the model will return a response.

ex: <br />
*Request*
```aidl
curl https://api.openai.com/v1/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -d '{
  "model": "gpt-3.5-turbo",
  "messages": [
    {
      "role": "user",
      "content": "What is LLM?"
    }
  ],
  "temperature": 1,
  "max_tokens": 256,
  "top_p": 1,
  "frequency_penalty": 0,
  "presence_penalty": 0
}'
```
Mandatory parameters
- Model
- Messages

Optional parameters
- temperature: Default 1, range 0~2, this parameter regulates the randomness of the response, higher -> increase randomness, lower -> increase focus


*Response*
```aidl
{
  "id": "chatcmpl-123",
  "object": "chat.completion",
  "created": 1677652288,
  "model": "gpt-3.5-turbo-0613",
  "system_fingerprint": "fp_44709d6fcb",
  "choices": [{
    "index": 0,
    "message": {
      "role": "assistant",
      "content": "\n\nHello there, how may I assist you today?",
    },
    "logprobs": null,
    "finish_reason": "stop"
  }],
  "usage": {
    "prompt_tokens": 9,
    "completion_tokens": 12,
    "total_tokens": 21
  }
}
```
- completion_tokens: response # of tokens

(c) openai endpoint - **Images**
```POST https://api.openai.com/v1/images/generations```
Creates an image given a prompt.

ex: <br />
*Request*
```aidl
curl https://api.openai.com/v1/images/generations \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $OPENAI_API_KEY" \
  -d '{
    "model": "dall-e-3",
    "prompt": "A cute baby sea otter",
    "n": 1,
    "size": "1024x1024"
  }'

```
Optional parameters
- n: Number of images to generate, default 1, max 10
- response_format: 'url' or 'b64_json', defaults 'url'
- 
*Response*
```aidl
{
  "created": 1589478378,
  "data": [
    {
      "url": "https://..."
    },
    {
      "url": "https://..."
    }
  ]
}
```


## Steps
(1) [Create API Key](https://platform.openai.com/api-keys)