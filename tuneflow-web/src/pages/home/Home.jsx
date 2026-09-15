import { useEffect, useState } from "react";
import api from "../../services/api";
import SongCard from "../../components/music/SongCard";
import { usePlayer } from "../../context/PlayerContext";

function Home() {

    const { playSong } = usePlayer();

    const [songs, setSongs] = useState([]);
    const [likedSongIds, setLikedSongIds] = useState([]);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");


    // =================================
    // Fetch Songs
    // =================================

    useEffect(() => {

        const fetchSongs = async () => {

            try {

                const response = await api.get("/songs");

                setSongs(response.data);

            } catch (error) {

                console.error(
                    "Failed to load songs:",
                    error
                );

                setError("Failed to load songs.");

            } finally {

                setLoading(false);

            }
        };

        fetchSongs();

    }, []);


    // =================================
    // Fetch Liked Songs
    // =================================

    useEffect(() => {

        const fetchLikedSongs = async () => {

            try {

                const response = await api.get("/likes");

                const ids = response.data.map(
                    (like) => like.songId
                );

                setLikedSongIds(ids);

            } catch (error) {

                console.error(
                    "Failed to load liked songs:",
                    error
                );

            }

        };

        fetchLikedSongs();

    }, []);


    // =================================
    // Like / Unlike
    // =================================

    const handleLike = async (song) => {

        const isLiked = likedSongIds.includes(song.id);

        try {

            if (isLiked) {

                await api.delete(
                    `/likes/${song.id}`
                );

                setLikedSongIds((previous) =>
                    previous.filter(
                        (id) => id !== song.id
                    )
                );

            } else {

                await api.post(
                    `/likes/${song.id}`
                );

                setLikedSongIds((previous) => [
                    ...previous,
                    song.id,
                ]);

            }

        } catch (error) {

            console.error(
                "Like/unlike failed:",
                error
            );

        }
    };


    // =================================
    // Play Song
    // =================================

    const handlePlay = (song) => {

        playSong(song);

    };


    return (

        <div className="space-y-10 max-w-7xl mx-auto">


            {/* ================================= */}
            {/* Welcome */}
            {/* ================================= */}

            <section>

                <h1 className="text-3xl md:text-4xl font-bold">
                    Good afternoon 👋
                </h1>

                <p className="text-gray-400 mt-2">
                    Discover music you'll love.
                </p>

            </section>


            {/* ================================= */}
            {/* Quick Picks */}
            {/* ================================= */}

            <section>

                <h2 className="text-2xl font-bold mb-5">
                    Quick Picks
                </h2>

                <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">

                    {[
                        "Made for You",
                        "Recently Played",
                        "Liked Songs",
                        "New Releases",
                        "Trending Now",
                    ].map((item) => (

                        <div
                            key={item}
                            className="bg-zinc-900 hover:bg-zinc-800 rounded-xl p-4 cursor-pointer transition"
                        >

                            <div className="aspect-square bg-zinc-800 rounded-lg mb-4 flex items-center justify-center text-4xl">
                                🎵
                            </div>

                            <h3 className="font-semibold truncate">
                                {item}
                            </h3>

                            <p className="text-sm text-gray-500 mt-1">
                                Discover music
                            </p>

                        </div>

                    ))}

                </div>

            </section>


            {/* ================================= */}
            {/* Recommended Songs */}
            {/* ================================= */}

            <section>

                <h2 className="text-2xl font-bold mb-5">
                    Recommended for You
                </h2>


                {/* Loading */}

                {loading && (

                    <p className="text-gray-500">
                        Loading songs...
                    </p>

                )}


                {/* Error */}

                {error && (

                    <p className="text-red-400">
                        {error}
                    </p>

                )}


                {/* Empty */}

                {!loading &&
                    !error &&
                    songs.length === 0 && (

                        <p className="text-gray-500">
                            No songs available.
                        </p>

                    )}


                {/* Songs */}

                {!loading &&
                    !error &&
                    songs.length > 0 && (

                        <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 xl:grid-cols-6 gap-4 md:gap-5">

                            {songs.map((song) => (

                                <SongCard
                                    key={song.id}
                                    song={song}
                                    isLiked={likedSongIds.includes(
                                        song.id
                                    )}
                                    onPlay={handlePlay}
                                    onLike={handleLike}
                                />

                            ))}

                        </div>

                    )}

            </section>


            {/* ================================= */}
            {/* Popular Artists */}
            {/* ================================= */}

            <section>

                <h2 className="text-2xl font-bold mb-5">
                    Popular Artists
                </h2>

                <div className="flex gap-6 overflow-x-auto pb-4">

                    {[1, 2, 3, 4, 5].map((item) => (

                        <div
                            key={item}
                            className="min-w-32 sm:min-w-36 text-center cursor-pointer"
                        >

                            <div className="w-32 h-32 sm:w-36 sm:h-36 mx-auto rounded-full bg-zinc-900 flex items-center justify-center text-5xl">
                                👤
                            </div>

                            <h3 className="font-medium mt-3">
                                Artist {item}
                            </h3>

                            <p className="text-sm text-gray-500">
                                Artist
                            </p>

                        </div>

                    ))}

                </div>

            </section>


        </div>

    );
}

export default Home;