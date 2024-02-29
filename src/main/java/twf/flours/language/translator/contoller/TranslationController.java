package twf.flours.language.translator.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

@RestController
@RequestMapping("/api/v1")
public class TranslationController {

	private static final String TARGET_LANGUAGE = "fr"; 
	private static final String API_KEY = "YOUR_API_KEY";

	@PostMapping("/translate")
	public ResponseEntity<Object> translateToFrench(@RequestBody TranslationRequest request) {
		if (request == null || request.getText() == null || request.getText().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing or invalid request body");
		}

		String translatedText = translateTextToFrench(request.getText());

		if (translatedText == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during translation");
		}

		TranslationResponse response = new TranslationResponse(translatedText);
		return ResponseEntity.ok(response);
	}

	private String translateTextToFrench(String text) {
		com.google.cloud.translate.Translate translate = TranslateOptions.newBuilder().setApiKey(API_KEY).build()
				.getService();

		Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage(TARGET_LANGUAGE));

		return translation.getTranslatedText();
	}
}
