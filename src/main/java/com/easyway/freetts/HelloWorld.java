package com.easyway.freetts;

import java.util.Locale;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class HelloWorld {

    public HelloWorld() {
    }

    public static void main(String args[]) {
        try {
            SynthesizerModeDesc desc = new SynthesizerModeDesc("FreeTTS en_US general synthesizer", "general",
                    Locale.US, null, null);
            Synthesizer synthesizer = Central.createSynthesizer(desc);
            if (synthesizer == null) {
                System.exit(1);
            }
            synthesizer.allocate();
            synthesizer.resume();
            desc = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
            Voice voices[] = desc.getVoices();
            if(voices != null && voices.length > 0){
                synthesizer.getSynthesizerProperties().setVoice(voices[0]);
                synthesizer.speakPlainText("MPLS alarm: link down", null);
                synthesizer.waitEngineState(0x10000L);
            }
            synthesizer.deallocate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}