import {
    createContext,
    useContext,
    useRef,
    useState,
} from "react";

import api from "../services/api";

const PlayerContext = createContext(null);

export function PlayerProvider({ children }) {

    const audioRef = useRef(null);

    const [currentSong, setCurrentSong] = useState(null);
    const [isPlaying, setIsPlaying] = useState(false);


    // ==========================================
    // PLAY SONG
    // ==========================================

    const playSong = async (song) => {

        if (!song?.audioUrl) {
            console.error("Song has no audio URL");
            return;
        }

        setCurrentSong(song);


        // Record Recently Played
        try {

            await api.post(
                `/recently-played/${song.id}`
            );

        } catch (error) {

            console.error(
                "Failed to record recently played:",
                error
            );

        }


        // Play audio
        if (audioRef.current) {

            audioRef.current.src = song.audioUrl;

            try {

                await audioRef.current.play();

                setIsPlaying(true);

            } catch (error) {

                console.error(
                    "Audio playback failed:",
                    error
                );

                setIsPlaying(false);

            }
        }
    };


    // ==========================================
    // PAUSE SONG
    // ==========================================

    const pauseSong = () => {

        if (audioRef.current) {
            audioRef.current.pause();
        }

        setIsPlaying(false);
    };


    // ==========================================
    // RESUME SONG
    // ==========================================

    const resumeSong = async () => {

        if (!currentSong || !audioRef.current) {
            return;
        }

        try {

            await audioRef.current.play();

            setIsPlaying(true);

        } catch (error) {

            console.error(
                "Audio resume failed:",
                error
            );

            setIsPlaying(false);

        }
    };


    // ==========================================
    // TOGGLE PLAY / PAUSE
    // ==========================================

    const togglePlay = async () => {

        if (!currentSong) {
            return;
        }

        if (isPlaying) {

            pauseSong();

        } else {

            await resumeSong();

        }
    };


    // ==========================================
    // PROVIDER
    // ==========================================

    return (
        <PlayerContext.Provider
            value={{
                currentSong,
                isPlaying,
                playSong,
                pauseSong,
                resumeSong,
                togglePlay,
            }}
        >

            {children}


            {/* Hidden Audio Player */}

            <audio
                ref={audioRef}
                preload="metadata"

                onEnded={() => {
                    setIsPlaying(false);
                }}

                onPause={() => {
                    setIsPlaying(false);
                }}

                onPlay={() => {
                    setIsPlaying(true);
                }}
            />

        </PlayerContext.Provider>
    );
}


// ==========================================
// CUSTOM HOOK
// ==========================================

export function usePlayer() {
    return useContext(PlayerContext);
}