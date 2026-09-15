import {
    Heart,
    Clock3,
    Music2,
    Users,
    Plus,
    Play,
} from "lucide-react";

import { useEffect, useState } from "react";
import api from "../../services/api";
import { usePlayer } from "../../context/PlayerContext";

function Library() {

    const { playSong } = usePlayer();

    const [recentlyPlayed, setRecentlyPlayed] = useState([]);
    const [loadingRecent, setLoadingRecent] = useState(true);


    // ==========================================
    // LOAD RECENTLY PLAYED
    // ==========================================

    useEffect(() => {

        const fetchRecentlyPlayed = async () => {

            try {

                const response = await api.get(
                    "/recently-played"
                );

                setRecentlyPlayed(response.data);

            } catch (error) {

                console.error(
                    "Failed to load recently played:",
                    error
                );

            } finally {

                setLoadingRecent(false);

            }
        };

        fetchRecentlyPlayed();

    }, []);


    // ==========================================
    // LIBRARY CARDS
    // ==========================================

    const items = [
        {
            title: "Liked Songs",
            description: "Songs you liked",
            icon: Heart,
        },
        {
            title: "Recently Played",
            description: "Your listening history",
            icon: Clock3,
        },
        {
            title: "Your Playlists",
            description: "Create and manage playlists",
            icon: Music2,
        },
        {
            title: "Following",
            description: "Artists you follow",
            icon: Users,
        },
    ];


    return (
        <div className="max-w-6xl mx-auto space-y-8">


            {/* ==========================================
                HEADER
            ========================================== */}

            <section className="flex flex-col sm:flex-row sm:items-end sm:justify-between gap-4">

                <div>

                    <h1 className="text-3xl md:text-4xl font-bold">
                        Your Library
                    </h1>

                    <p className="text-gray-400 mt-2">
                        Your music, playlists and favorites.
                    </p>

                </div>


                <button
                    className="flex items-center justify-center gap-2 bg-white text-black font-semibold px-5 py-2.5 rounded-full hover:bg-gray-200 transition"
                >
                    <Plus size={18} />
                    Create Playlist
                </button>

            </section>


            {/* ==========================================
                LIBRARY CARDS
            ========================================== */}

            <section>

                <div className="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 gap-4">

                    {items.map((item) => {

                        const Icon = item.icon;

                        return (
                            <div
                                key={item.title}
                                className="bg-zinc-900 hover:bg-zinc-800 rounded-xl p-4 md:p-5 cursor-pointer transition"
                            >

                                <div className="w-12 h-12 md:w-14 md:h-14 rounded-lg bg-zinc-800 flex items-center justify-center mb-5">

                                    <Icon size={24} />

                                </div>


                                <h2 className="font-semibold truncate">
                                    {item.title}
                                </h2>


                                <p className="text-sm text-gray-500 mt-1">
                                    {item.description}
                                </p>

                            </div>
                        );

                    })}

                </div>

            </section>


            {/* ==========================================
                RECENTLY PLAYED
            ========================================== */}

            <section>

                <div className="flex items-center justify-between mb-5">

                    <div>

                        <h2 className="text-2xl font-bold">
                            Recently Played
                        </h2>

                        <p className="text-gray-500 text-sm mt-1">
                            Songs you recently listened to.
                        </p>

                    </div>

                </div>


                {loadingRecent ? (

                    <div className="text-gray-500 py-8 text-center">
                        Loading recently played...
                    </div>

                ) : recentlyPlayed.length === 0 ? (

                    <div className="rounded-xl border border-dashed border-zinc-700 p-8 md:p-12 text-center">

                        <div className="text-5xl mb-4">
                            🎧
                        </div>

                        <h3 className="text-lg font-semibold">
                            No recently played songs
                        </h3>

                        <p className="text-gray-500 mt-2">
                            Start listening to see your history here.
                        </p>

                    </div>

                ) : (

                    <div className="space-y-2">

                        {recentlyPlayed.map((song) => (

                            <div
                                key={song.id}
                                className="group flex items-center gap-3 md:gap-4 p-3 rounded-lg hover:bg-zinc-900 transition"
                            >

                                {/* Cover */}

                                <div className="relative w-12 h-12 md:w-14 md:h-14 shrink-0 rounded-md bg-zinc-800 overflow-hidden">

                                    {song.coverImage ? (

                                        <img
                                            src={song.coverImage}
                                            alt={song.songTitle}
                                            className="w-full h-full object-cover"
                                        />

                                    ) : (

                                        <div className="w-full h-full flex items-center justify-center text-xl">
                                            🎵
                                        </div>

                                    )}


                                    {/* Play Button */}

                                    <button
                                        onClick={() =>
                                            playSong({
                                                id: song.songId,
                                                title: song.songTitle,
                                                audioUrl: song.audioUrl,
                                                coverImage: song.coverImage,
                                                duration: song.duration,
                                                artistId: song.artistId,
                                                artistName: song.artistName,
                                                albumId: song.albumId,
                                                albumTitle: song.albumTitle,
                                            })
                                        }
                                        className="absolute inset-0 flex items-center justify-center bg-black/50 opacity-0 group-hover:opacity-100 transition"
                                    >

                                        <Play
                                            size={20}
                                            fill="white"
                                        />

                                    </button>

                                </div>


                                {/* Song Information */}

                                <div className="flex-1 min-w-0">

                                    <p className="font-medium truncate">
                                        {song.songTitle}
                                    </p>

                                    <p className="text-sm text-gray-500 truncate">
                                        {song.artistName || "Unknown Artist"}
                                    </p>

                                </div>


                                {/* Album */}

                                <div className="hidden md:block w-1/4 min-w-0">

                                    <p className="text-sm text-gray-500 truncate">
                                        {song.albumTitle || "Single"}
                                    </p>

                                </div>


                                {/* Duration */}

                                <div className="hidden sm:block text-sm text-gray-500">

                                    {song.duration || "0:00"}

                                </div>

                            </div>

                        ))}

                    </div>

                )}

            </section>


            {/* ==========================================
                PLAYLISTS
            ========================================== */}

            <section>

                <h2 className="text-2xl font-bold mb-5">
                    Your Playlists
                </h2>


                <div className="rounded-xl border border-dashed border-zinc-700 p-8 md:p-12 text-center">

                    <div className="text-5xl mb-4">
                        🎵
                    </div>

                    <h3 className="text-lg font-semibold">
                        No playlists yet
                    </h3>

                    <p className="text-gray-500 mt-2">
                        Create your first playlist and start adding songs.
                    </p>


                    <button
                        className="mt-5 bg-white text-black font-semibold px-5 py-2.5 rounded-full hover:bg-gray-200 transition"
                    >
                        Create Playlist
                    </button>

                </div>

            </section>

        </div>
    );
}

export default Library;