package com.doconnectai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.doconnectai.dto.QuestionDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiService {

	@Value("${gemini.api.key}")
	private String apiKey;

	private final RestTemplate restTemplate = new RestTemplate();

	public String generateAnswer(QuestionDto dto) throws Exception {

		String prompt = """
				Answer the following question.

				Title:
				%s

				Description:
				%s
				""".formatted(dto.getTitle(), dto.getDescription());

		String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
				+ apiKey;

		String requestBody = """
				{
				  "contents": [
				    {
				      "parts": [
				        {
				          "text": "%s"
				        }
				      ]
				    }
				  ]
				}
				""".formatted(prompt.replace("\"", "\\\""));

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(response.getBody());

		return root.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
	}
}