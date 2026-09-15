import {
    Play,
    Heart,
    MoreHorizontal,
} from "lucide-react";

function SongCard({ song, isLiked, onPlay, onLike }) {
    return (
        <div className="group bg-zinc-900 hover:bg-zinc-800 rounded-xl p-4 transition">

            {/* Cover */}
            <div className="relative aspect-square rounded-lg bg-zinc-800 overflow-hidden">

                {song.coverImage ? (
                    <img
                        src={song.coverImage}
                        alt={song.title}
                        className="w-full h-full object-cover"
                    />
                ) : (
                    <div className="w-full h-full flex items-center justify-center text-5xl">
                        🎵
                    </div>
                )}

                {/* Play Button */}
                <button
                    onClick={() => onPlay?.(song)}
                    className="absolute bottom-3 right-3 w-11 h-11 rounded-full bg-white text-black flex items-center justify-center opacity-0 translate-y-2 group-hover:opacity-100 group-hover:translate-y-0 transition"
                >
                    <Play
                        size={19}
                        fill="black"
                        className="ml-0.5"
                    />
                </button>

            </div>

            {/* Song Details */}
            <div className="mt-4 min-w-0">

                <div className="flex items-center justify-between gap-2">

                    {/* Song Title */}
                    <h3 className="font-semibold truncate">
                        {song.title}
                    </h3>

                    {/* Like Button */}
                    <button
                        onClick={() => onLike?.(song)}
                        className={`shrink-0 transition ${
                            isLiked
                                ? "text-white"
                                : "text-gray-500 hover:text-white"
                        }`}
                    >
                        <Heart
                            size={18}
                            fill={isLiked ? "currentColor" : "none"}
                        />
                    </button>

                </div>

                {/* Artist */}
                <p className="text-sm text-gray-500 truncate mt-1">
                    {song.artistName || "Unknown Artist"}
                </p>

                {/* Album */}
                {song.albumTitle && (
                    <p className="text-xs text-gray-600 truncate mt-1">
                        {song.albumTitle}
                    </p>
                )}

            </div>

            {/* More Options */}
            <button
                className="mt-3 text-gray-600 hover:text-white transition"
            >
                <MoreHorizontal size={18} />
            </button>

        </div>
    );
}

export default SongCard;