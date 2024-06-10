package modules;

import marytts.LocalMaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.htsengine.HMMVoice;
import marytts.modules.synthesis.Voice;
import marytts.modules.synthesis.WaveformSynthesizer;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

import org.w3c.dom.Element;

//import com.techventus.server.voice.*;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MaryTTSExample {

    public static void main(String[] args) throws MaryConfigurationException, SynthesisException, LineUnavailableException, IOException {
        // Initialize the MaryTTS interface
        LocalMaryInterface mary = new LocalMaryInterface();
        //mary.setLocale(Locale.forLanguageTag("ru"));
        mary.setLocale(Locale.US);
        mary.getAvailableVoices().stream().forEach(System.out::println);
        
        
        mary.setVoice("cmu-slt-hsmm");
        
        
        WaveformSynthesizer synthesizer = new WaveformSynthesizer() {
			/*
			@Override
			public AudioInputStream synthesize(List<Element> arg0, Voice arg1, String arg2) throws SynthesisException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void startup() throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void powerOnSelfTest() throws Error {
				// TODO Auto-generated method stub
				
			}*/
        	
        	
        	private boolean started = false;
            
            @Override
            public AudioInputStream synthesize(List<Element> arg0, Voice arg1, String arg2) throws SynthesisException {
                // Implementation for synthesizing the speech elements
                // This method takes a list of speech elements (e.g., text, phonemes, prosody tags)
                // a voice object, and a synthesis context string, and returns an AudioInputStream
                
                // You can use MaryTTS's MaryInterface to synthesize the speech elements
                LocalMaryInterface mary = new LocalMaryInterface();
                mary.setVoice(arg1.toString());
                mary.setInputType("RAWMARYXML");
                mary.setOutputType("AUDIO");
                mary.setLocale(arg1.getLocale());
                mary.process(arg0, arg2);
                
                mary.p
                
                
                return mary.getAudio();
            }
            
            @Override
            public void startup() throws Exception {
                // Implementation for startup code
                // This method is called once before synthesizing any speech,
                // and can be used for initializing resources and dependencies
                
                // In this example, we simply print a message to the console
                System.out.println("WaveformSynthesizer is starting up...");
                started = true;
            }
            
            @Override
            public void powerOnSelfTest() throws Error {
                // Implementation for self-test code
                // This method is called once after startup,
                // and can be used for checking the availability and consistency of resources
                
                // In this example, we simply check that the started flag is set to true
                if (!started) {
                    throw new Error("WaveformSynthesizer did not start up properly.");
                }
            }
        	
        	
		};    //(WaveformSynthesizer) mary.getd   //.getSynthesizer();
        	//	WaveformSynthesizerAudioPlayer
        
        Voice.Gender MALE = new Voice.Gender("male");
        Voice.Gender FEMALE = new Voice.Gender("female");
        
        Voice v = new Voice("cmu-rms-hsmm", Locale.US, AudioFormat.WAV, )// = mary.getvoi   .getVoice();
        		
        		/*
        		avem clasa voice cu constructorul Voice(String name, Locale locale, AudioFormat dbAudioFormat, WaveformSynthesizer synthesizer, Voice.Gender gender)
        		
        		name este denumirea la voice
        		locale am pus Locale.US
        		dbAudioFormat am creat asa o instanta
        		dar de unde sa iau valoarea synthesizer????
        		ma intelegi despre ce te intreb, mie imi dai niste exemple nerealizabile, cu erori si ne functionale pentru scopul meu
        		
        		*/
        
        System.out.println(v.gender().toString());
        
        HMMVoice maryVoice = new HMMVoice(voiceName, synthesizer)      //("my_voice", new Locale("en_US"));
        maryVoice.setVoiceName("cmu-slt-hsmm");
        maryVoice.setGender(Voice.Gender.FEMALE);

        Voice mary = new Voice("my_voice", new Locale("en_US"), AudioFormat.WAV, maryVoice, Voice.Gender.FEMALE);
        

        // Generate speech from text
        //String text = "Привет, как дела?Продукты с высоким содержанием витамина D положительно влияют на работу сердечно-сосудистой системы и оказывают на нее защитное действие, выяснили греческие ученые.";
        
        String text = "Hello world, how are you, in this text message I will speech something, and you don't ignore me";
        AudioInputStream audio = mary.generateAudio(text);

        // Play the audio
        //javax.sound.sampled.AudioSystem.getClip().open(audio).start();
        
        //voice.
        
        //AudioSystem.getClip().open(audio);
        
        
        //javax.sound.sampled.AudioSystem.getClip().open(audio);
        
        saveAudioToMp3(audio, "hello.mp3");
        
    }
    
    
    private static void saveAudioToMp3(AudioInputStream audioInputStream, String outputFilePath) {
        try {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(outputFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}


