import {
    Play,
    Pause,
    SkipBack,
    SkipForward,
    Volume2,
} from "lucide-react";

import { usePlayer } from "../../context/PlayerContext";

function MusicPlayer() {

    const {
        currentSong,
        isPlaying,
        togglePlay,
    } = usePlayer();


    return (
        <footer className="fixed bottom-0 left-0 md:left-64 right-0 h-20 bg-zinc-900 border-t border-zinc-800 z-50">

            <div className="h-full flex items-center justify-between px-3 md:px-6">


                {/* ================================= */}
                {/* Current Song */}
                {/* ================================= */}

                <div className="flex items-center gap-2 md:gap-3 w-1/3 min-w-0">

                    <div className="w-11 h-11 md:w-12 md:h-12 shrink-0 rounded-md bg-zinc-800 overflow-hidden flex items-center justify-center">

                        {currentSong?.coverImage ? (

                            <img
                                src={currentSong.coverImage}
                                alt={currentSong.title}
                                className="w-full h-full object-cover"
                            />

                        ) : (

                            <span className="text-xl">
                                🎵
                            </span>

                        )}

                    </div>


                    <div className="min-w-0">

                        <p className="text-xs md:text-sm font-medium truncate">

                            {currentSong?.title ||
                                "No song playing"}

                        </p>


                        <p className="text-[10px] md:text-xs text-gray-500 truncate">

                            {currentSong?.artistName ||
                                "Select a song"}

                        </p>

                    </div>

                </div>


                {/* ================================= */}
                {/* Controls */}
                {/* ================================= */}

                <div className="flex flex-col items-center gap-1">

                    <div className="flex items-center gap-4 md:gap-5">


                        {/* Previous */}

                        <button
                            disabled={!currentSong}
                            className="hidden sm:block text-gray-400 hover:text-white disabled:opacity-30 transition"
                        >

                            <SkipBack size={19} />

                        </button>


                        {/* Play / Pause */}

                        <button
                            onClick={togglePlay}
                            disabled={!currentSong}
                            className="w-9 h-9 rounded-full bg-white text-black flex items-center justify-center hover:scale-105 disabled:opacity-40 disabled:hover:scale-100 transition"
                        >

                            {isPlaying ? (

                                <Pause size={18} />

                            ) : (

                                <Play
                                    size={18}
                                    className="ml-0.5"
                                />

                            )}

                        </button>


                        {/* Next */}

                        <button
                            disabled={!currentSong}
                            className="hidden sm:block text-gray-400 hover:text-white disabled:opacity-30 transition"
                        >

                            <SkipForward size={19} />

                        </button>

                    </div>


                    {/* ================================= */}
                    {/* Progress */}
                    {/* ================================= */}

                    <div className="hidden sm:flex items-center gap-2 text-[10px] text-gray-500">

                        <span>
                            0:00
                        </span>


                        <div className="w-32 md:w-64 h-1 bg-zinc-700 rounded-full overflow-hidden">

                            <div className="w-0 h-full bg-white rounded-full" />

                        </div>


                        <span>
                            {currentSong?.duration || "0:00"}
                        </span>

                    </div>

                </div>


                {/* ================================= */}
                {/* Volume */}
                {/* ================================= */}

                <div className="hidden sm:flex items-center gap-3 w-1/3 justify-end">

                    <Volume2
                        size={19}
                        className="text-gray-400"
                    />


                    <div className="w-20 md:w-24 h-1 bg-zinc-700 rounded-full">

                        <div className="w-3/4 h-full bg-white rounded-full" />

                    </div>

                </div>


            </div>

        </footer>
    );
}

export default MusicPlayer;