import {
    Heart,
    Play,
    MoreHorizontal,
} from "lucide-react";
import { useEffect, useState } from "react";
import api from "../../services/api";

function LikedSongs() {
    const [songs, setSongs] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        const fetchLikedSongs = async () => {
            try {
                const response = await api.get("/likes");

                setSongs(response.data);
            } catch (error) {
                console.error(error);
                setError("Failed to load liked songs.");
            } finally {
                setLoading(false);
            }
        };

        fetchLikedSongs();
    }, []);
    return (
        <div className="max-w-6xl mx-auto space-y-8">

            {/* Header */}
            <section className="flex flex-col sm:flex-row sm:items-end gap-5">

                <div className="w-32 h-32 md:w-40 md:h-40 rounded-xl bg-gradient-to-br from-purple-700 to-blue-500 flex items-center justify-center shrink-0">
                    <Heart
                        size={56}
                        fill="white"
                        className="text-white"
                    />
                </div>

                <div>

                    <p className="text-sm text-gray-400 uppercase tracking-wide">
                        Playlist
                    </p>

                    <h1 className="text-3xl md:text-5xl font-bold mt-2">
                        Liked Songs
                    </h1>

                    <p className="text-gray-400 mt-3">
                        Songs you've liked on TuneFlow
                    </p>

                </div>

            </section>

            {/* Play Button */}
            <button className="w-12 h-12 rounded-full bg-white text-black flex items-center justify-center hover:scale-105 transition">
                <Play
                    size={21}
                    fill="black"
                    className="ml-0.5"
                />
            </button>

            {/* Songs */}
            <section>

                <div className="hidden sm:grid grid-cols-[40px_1fr_1fr_80px] gap-4 px-4 py-3 text-xs text-gray-500 border-b border-zinc-800">
                    <span>#</span>
                    <span>Title</span>
                    <span>Album</span>
                    <span>Time</span>
                </div>

                {loading && (
                    <div className="py-12 text-center text-gray-500">
                        Loading liked songs...
                    </div>
                )}

                {error && (
                    <div className="py-12 text-center text-red-400">
                        {error}
                    </div>
                )}

                {!loading && !error && songs.length === 0 && (
                    <div className="py-12 text-center">

                        <div className="text-5xl mb-4">
                            💔
                        </div>

                        <h3 className="text-lg font-semibold">
                            No liked songs yet
                        </h3>

                        <p className="text-gray-500 mt-2">
                            Songs you like will appear here.
                        </p>

                    </div>
                )}

                {!loading && !error && songs.map((song, index) => (

                    <div
                        key={song.id}
                        className="group grid grid-cols-[40px_1fr_auto] sm:grid-cols-[40px_1fr_1fr_80px] gap-4 items-center px-4 py-3 rounded-lg hover:bg-zinc-900 transition"
                    >

                        {/* Number */}
                        <span className="text-sm text-gray-500 group-hover:hidden">
      {index + 1}
    </span>

                        <button className="hidden group-hover:flex items-center justify-center">
                            <Play
                                size={16}
                                fill="white"
                            />
                        </button>

                        {/* Song */}
                        <div className="flex items-center gap-3 min-w-0">

                            <div className="w-11 h-11 rounded-md bg-zinc-800 overflow-hidden shrink-0">

                                {song.coverImage ? (
                                    <img
                                        src={song.coverImage}
                                        alt={song.songTitle}
                                        className="w-full h-full object-cover"
                                    />
                                ) : (
                                    <div className="w-full h-full flex items-center justify-center">
                                        🎵
                                    </div>
                                )}

                            </div>

                            <div className="min-w-0">

                                <p className="font-medium truncate">
                                    {song.songTitle}
                                </p>

                                <p className="text-sm text-gray-500 truncate">
                                    {song.artistName}
                                </p>

                            </div>

                        </div>

                        {/* Album */}
                        <p className="hidden sm:block text-sm text-gray-400 truncate">
                            {song.albumTitle || "Single"}
                        </p>

                        {/* Actions */}
                        <div className="flex items-center justify-end gap-3">

      <span className="hidden sm:block text-sm text-gray-500">
        {song.duration || "--:--"}
      </span>

                            <button className="text-gray-500 hover:text-white">
                                <MoreHorizontal size={19} />
                            </button>

                        </div>

                    </div>

                ))}

            </section>

        </div>
    );
}

export default LikedSongs;