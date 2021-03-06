package uk.co.caprica.vlcj.player;

import uk.co.caprica.vlcj.binding.internal.libvlc_media_stats_t;

public class MediaStatistics {

    private int   inputBytesRead;
    private float inputBitrate;
    private int   demuxBytesRead;
    private float demuxBitRate;
    private int   demuxCorrupted;
    private int   demuxDiscontinuity;
    private int   decodedVideo;
    private int   decodedAudio;
    private int   picturesDisplayed;
    private int   picturesLost;
    private int   audioBuffersPlayed;
    private int   audioBuffersLost;
    private int   sentPackets;
    private int   sentBytes;
    private float sendBitrate;

    public int inputBytesRead() {
        return inputBytesRead;
    }

    public float inputBitrate() {
        return inputBitrate;
    }

    public int demuxBytesRead() {
        return demuxBytesRead;
    }

    public float demuxBitRate() {
        return demuxBitRate;
    }

    public int demuxCorrupted() {
        return demuxCorrupted;
    }

    public int demuxDiscontinuity() {
        return demuxDiscontinuity;
    }

    public int decodedVideo() {
        return decodedVideo;
    }

    public int decodedAudio() {
        return decodedAudio;
    }

    public int picturesDisplayed() {
        return picturesDisplayed;
    }

    public int picturesLost() {
        return picturesLost;
    }

    public int audioBuffersPlayed() {
        return audioBuffersPlayed;
    }

    public int audioBuffersLost() {
        return audioBuffersLost;
    }

    public int sentPackets() {
        return sentPackets;
    }

    public int sentBytes() {
        return sentBytes;
    }

    public float sendBitrate() {
        return sendBitrate;
    }

    public final void apply(libvlc_media_stats_t stats) {
        this.inputBytesRead     = stats.i_read_bytes;
        this.inputBitrate       = stats.f_input_bitrate;
        this.demuxBytesRead     = stats.i_demux_read_bytes;
        this.demuxBitRate       = stats.f_demux_bitrate;
        this.demuxCorrupted     = stats.i_demux_corrupted;
        this.demuxDiscontinuity = stats.i_demux_discontinuity;
        this.decodedVideo       = stats.i_decoded_video;
        this.decodedAudio       = stats.i_decoded_audio;
        this.picturesDisplayed  = stats.i_displayed_pictures;
        this.picturesLost       = stats.i_lost_pictures;
        this.audioBuffersPlayed = stats.i_played_abuffers;
        this.audioBuffersLost   = stats.i_lost_abuffers;
        this.sentPackets        = stats.i_sent_packets;
        this.sentBytes          = stats.i_sent_bytes;
        this.sendBitrate        = stats.f_send_bitrate;
    }
    
}
