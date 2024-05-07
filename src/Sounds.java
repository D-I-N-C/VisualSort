import java.util.concurrent.LinkedTransferQueue;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sounds {
    public static final int SAMPLE_RATE_HZ = 48000;
    LinkedTransferQueue<Tone> queue;
    SourceDataLine line;
    boolean isRunning;
    Thread thread;

    static class Tone {
        public double frequency_hz;
        public long length_ms;

        public Tone(double frequency_hz, long length_ms) {
            this.frequency_hz = frequency_hz;
            this.length_ms = length_ms;
        }
    }

    public Sounds() throws LineUnavailableException {
        queue = new LinkedTransferQueue<>();
        AudioFormat audioFormat = new AudioFormat(SAMPLE_RATE_HZ, 8, 1, true, true);
        line = AudioSystem.getSourceDataLine(audioFormat);
        line.open(audioFormat, SAMPLE_RATE_HZ);
        line.start();
        isRunning = true;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] waveform = new byte[50000];
                while (isRunning) {
                    Tone tone = queue.poll();
                    if (tone != null) {
                        int sampleCount = (int) (SAMPLE_RATE_HZ * tone.length_ms / 1000);
                        double period = (double) SAMPLE_RATE_HZ / tone.frequency_hz;
                        int pos = 0;
                        for (int i = 0; i < sampleCount; i++) {
                            double angle = 2.0 * Math.PI * i / period;
                            // double amplitude = Math.min(1.0,
                            //         Math.min(20.0 * i / sampleCount, 20.0 * (sampleCount - i - 1) / sampleCount));
                            // amplitude = amplitude * 20.0;
                            waveform[pos] = (byte) (Math.sin(angle) * 20);
                            pos++;
                            if (pos >= waveform.length){
                                line.write(waveform, 0, waveform.length);
                                pos = 0;
                            }
                        }
                        if (pos > 0) {
                            line.write(waveform, 0, pos);
                            pos = 0;
                        }
                    }
                }
            }
        });
        thread.start();
    }

    public void playTone(double frequency_hz, long length_ms) {
        Tone tone = new Tone(frequency_hz, length_ms);
        queue.add(tone);
    }

    public void close() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        line.close();
    }
}