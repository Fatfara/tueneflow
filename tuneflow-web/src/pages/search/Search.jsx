import { Search as SearchIcon, X } from "lucide-react";
import { useState } from "react";

function Search() {
    const [query, setQuery] = useState("");

    const clearSearch = () => {
        setQuery("");
    };

    return (
        <div className="max-w-6xl mx-auto space-y-8">

            {/* Header */}
            <section>
                <h1 className="text-3xl md:text-4xl font-bold">
                    Search
                </h1>

                <p className="text-gray-400 mt-2">
                    Find songs, artists, albums and genres.
                </p>
            </section>

            {/* Search Input */}
            <div className="relative max-w-2xl">

                <SearchIcon
                    size={21}
                    className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-500"
                />

                <input
                    type="text"
                    value={query}
                    onChange={(event) => setQuery(event.target.value)}
                    placeholder="What do you want to play?"
                    className="w-full h-12 md:h-14 bg-zinc-900 border border-zinc-800 rounded-full pl-12 pr-12 text-sm md:text-base text-white placeholder:text-gray-500 outline-none focus:border-zinc-500 transition"
                />

                {query && (
                    <button
                        onClick={clearSearch}
                        className="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-white"
                    >
                        <X size={20} />
                    </button>
                )}

            </div>

            {/* Empty State */}
            {!query && (
                <section className="py-16 text-center">

                    <div className="text-6xl mb-5">
                        🎧
                    </div>

                    <h2 className="text-xl font-semibold">
                        Start searching
                    </h2>

                    <p className="text-gray-500 mt-2">
                        Search for your favorite music and artists.
                    </p>

                </section>
            )}

            {/* Temporary Results */}
            {query && (
                <section>

                    <h2 className="text-xl md:text-2xl font-bold mb-5">
                        Search results
                    </h2>

                    <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-4">

                        {[1, 2, 3, 4, 5].map((item) => (
                            <div
                                key={item}
                                className="bg-zinc-900 hover:bg-zinc-800 rounded-xl p-4 transition cursor-pointer"
                            >

                                <div className="aspect-square rounded-lg bg-zinc-800 flex items-center justify-center text-4xl mb-4">
                                    🎵
                                </div>

                                <h3 className="font-medium truncate">
                                    Search Result {item}
                                </h3>

                                <p className="text-sm text-gray-500 truncate mt-1">
                                    TuneFlow Artist
                                </p>

                            </div>
                        ))}

                    </div>

                </section>
            )}

        </div>
    );
}

export default Search;