package Main;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTS {
    private Voice voice;
    private final String voiceName;

    public TTS(String voiceName) {
        this.voiceName = voiceName;
        initializeVoice();
    }

    private void initializeVoice() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(voiceName);
        if (voice != null) {
            voice.allocate();
            voice.setRate(150);
            voice.setVolume(2.0f);
        } else {
            throw new IllegalArgumentException("Voice not found: " + voiceName);
        }
    }

    public void speak(String text) {
        if (voice != null) {
            voice.speak(text);
        } else {
            System.err.println("Voice not available.");
        }
    }

    public void close() {
        if (voice != null) {
            voice.deallocate();
        }
    }
}
